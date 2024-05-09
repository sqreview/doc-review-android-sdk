package com.signalquest.api;

import static com.signalquest.swig.ntrip.Error_Status_t.Error_Status_200;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Return_t.NTRIP_NO_MESSAGE_PENDING;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Return_t.NTRIP_SUCCESS;
import static com.signalquest.swig.ntrip.NTRIP_Parse_Types_t.NTRIP_RTCM_Message;
import static com.signalquest.swig.ntrip.ntrip.NTRIP_Parse;
import static com.signalquest.swig.ntrip.ntrip.NTRIP_Parse_Init;
import static com.signalquest.swig.ntrip.ntrip.NTRIP_Parse_Next_Message;
import static com.signalquest.swig.ntrip.ntrip.getPollIndex;
import static com.signalquest.swig.ntrip.ntrip.getRTCMID;

import android.util.Log;

import androidx.annotation.NonNull;

import com.signalquest.swig.ntrip.Error_Status_t;
import com.signalquest.swig.ntrip.NTRIP_Header_Info_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Context_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Poll_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Return_t;
import com.signalquest.swig.ntrip.NTRIP_Parse_Types_t;

import java.util.Arrays;
import java.util.List;

/* TODO SignalPoint instead of SitePoint throughout Javadoc comments? */

/**
 * Parses the NTRIP authorization and RTCM messages.
 * <p>
 * The {@link RtcmReceiver} gets called for each full message received.
 * <p>
 * Only SitePoint-handled RTCM message types are reported.
 */
@SuppressWarnings("unused")
public class NtripParser {

    private final RtcmReceiver rtcmReceiver;
    private final static String LOG_TAG = "SQ NTRIP";
    private static boolean initialized = false;
    private NTRIP_Parse_Context_t context;
    private final StableBuffer buffer = new StableBuffer(2 * 65536);

    private final static List<Integer> allowedMessageTypes = Arrays.asList( // SitePoint recognizes these
            1001, // L1 - only GPS RTK observables
            1002, // Extended L1 - only GPS RTK observables
            1003, // L1 / L2 GPS RTK observables
            1004, // Extended L1 / L2 GPS RTK observables
            1005, // Stationary RTK reference station ARP
            1006, // Stationary RTK reference station ARP with antenna height
            1007, // Antenna descriptor
            1009, // L1 - only GLONASS RTK observables
            1010, // Extended L1 - only GLONASS RTK observables
            1011, // L1 / L2 GLONASS RTK observables
            1012, // Extended L1 / L2 GLONASS RTK observables
            1033, // Receiver and Antenna Description
            1074, // GPS MSM4
            1075, // GPS MSM5
            1077, // GPS MSM7
            1084, // GLONASS MSM4
            1085, // GLONASS MSM5
            1087, // GLONASS MSM7
            1094, // Galileo MSM4
            1095, // Galileo MSM5
            1097, // Galileo MSM7
            1124, // BeiDou MSM4
            1125, // BeiDou MSM5
            1127, // BeiDou MSM7
            1230, // GLONASS code - phase biases
            4072 // u-Blox Proprietary , 4072.0 Reference st
    );

    /**
     * Creates an NTRIP parser with the given {@link RtcmReceiver receiver}.
     */
    public NtripParser(@NonNull RtcmReceiver receiver) {
        initialize();
        this.rtcmReceiver = receiver;
    }

    private synchronized void initialize() {
        if (!initialized) {
            System.loadLibrary("ntrip_wrapper");
            context = new NTRIP_Parse_Context_t();
            NTRIP_Parse_Return_t libInitialized = NTRIP_Parse_Init(context, buffer.array(), buffer.capacity(), null);
            assert(NTRIP_SUCCESS == libInitialized);
            initialized = true;
        }
    }

    /**
     * Parses the RTCM messages.
     * <p>
     * Call after authorization is confirmed using {@link #parseAuthorized(byte[])}.
     * Results are sent to the given {@link RtcmReceiver}.
     *
     * @param data The bytes, after a successful authorization response, from the aiding NTRIP server
     */
    @SuppressWarnings("unused")
    public void parseRtcm(@NonNull byte[] data) {
        NTRIP_Parse(context, data, data.length);
        handleRtcmResults();
    }

    /**
     * Parses an NTRIP server authorization response.
     *
     * @param data The initial response bytes from an NTRIP server aiding request
     */
    @SuppressWarnings("unused")
    public void parseAuthorized(@NonNull byte[] data) throws AuthorizationFailure {
        NTRIP_Parse_Return_t myReturnStatus = NTRIP_Parse(context, data, data.length);
        NTRIP_Header_Info_t header = context.getHeader();
        if (header.getAuthorized()) {
            return;
        }
        NTRIP_Parse_Poll_t poll = context.getPoll();
        Error_Status_t status = header.getError_Status();
        String responseText = header.getFirst_HTTP_Reply_string();
        Log.w(LOG_TAG, String.format("Auth failed; %s; status/type: %s/%s (%s).", status, poll.getStatus(), poll.getType(), responseText));

        String reason = header.getShort_Text_string();
        String description = header.getDescription_string();
        throw new AuthorizationFailure(reason, description);
    }

    private void handleRtcmResults() {
        NTRIP_Parse_Poll_t poll = context.getPoll();
        NTRIP_Header_Info_t header = context.getHeader();
        NTRIP_Parse_Return_t status = poll.getStatus();
        NTRIP_Parse_Types_t type = poll.getType();

        //NOTE: getError_Status() will only update if a new header comes through.
        Error_Status_t httpStatus = header.getError_Status();
        if (httpStatus != Error_Status_200) {
            Log.w(LOG_TAG, "Unable to parse RTCM message " + header.getShort_Text_string() + ", " + header.getDescription_string());
            return;
        }
        if (status == NTRIP_MESSAGE_PENDING) {
            if (type == NTRIP_RTCM_Message) {
                final int start = (int) getPollIndex(context);
                final int length = (int) context.getPoll().getLength();
                byte[] rtcmMessage = new byte[length];
                System.arraycopy(buffer.array(), start, rtcmMessage, 0, length);
                int messageType = getRTCMID(rtcmMessage);
                if (messageType != -1 && allowedMessageTypes.contains(messageType)) {
                    rtcmReceiver.receive(rtcmMessage);
                    Log.d(LOG_TAG, "Processed RTCM message with id " + messageType);
                } else {
                    Log.d(LOG_TAG, "Unhandled message id " + messageType);
                }
            }
            NTRIP_Parse_Next_Message(context);
            handleRtcmResults();
        } else if (status == NTRIP_NO_MESSAGE_PENDING) {
            Log.d(LOG_TAG, "parseRtcm done, no message pending");
        } else {
            Log.w(LOG_TAG, "parseRtcm done with status " + status + " and type " + type);
        }
    }

    /**
     * Indicates an NTRIP authorization failure.
     */
    public static class AuthorizationFailure extends Exception {
        /**
         * The summary message for the authorization failure.
         */
        public String summary;
        /**
         * The detailed message for the authorization failure.
         */
        public String details;

        private AuthorizationFailure(String summary, String details) {
            this.summary = summary;
            this.details = details;
        }
    }

    /**
     * Receives RTCM messages, parsed with {@link NtripParser#parseRtcm(byte[])}, that can be written to a SitePoint RTCM characteristic.
     */
    public interface RtcmReceiver {
        /**
         * Callback that receives RTCM messages.
         */
        void receive(byte[] rtcmMessage);
    }
}
