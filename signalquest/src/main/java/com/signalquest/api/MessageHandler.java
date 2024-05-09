package com.signalquest.api;

import static com.signalquest.swig.sqsp.sqsp.sqspLlaCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusCopy;
import static com.signalquest.swig.sqtp.sqtp.SqtpCheckValue_tSize;
import static com.signalquest.swig.sqtp.sqtp.sqtpFrameReaderInit;
import static com.signalquest.swig.sqtp.sqtp.sqtpFrameReaderNextStruct;

import android.util.Log;

import com.signalquest.swig.sqsp.SqspLla_t;
import com.signalquest.swig.sqsp.SqspStatus_t;
import com.signalquest.swig.sqtp.SqtpFrameReader_t;
import com.signalquest.swig.sqtp.SqtpStatus_t;
import com.signalquest.swig.sqtp.SqtpSubframeId_t;

import static com.signalquest.swig.sqtp.SqtpSubframeId_t.*;

import androidx.annotation.NonNull;

import java.util.Arrays;

/**
 * Handles the SignalQuest Transport Protocol message parsing for SignalQuest sensors.
 * <p>
 * Parses {@link Status Status}
 * and {@link Location Location} data received from the message protocol
 * characteristic, using {@link #parse(byte[])}.
 * <p>
 * Receive parsed messages using {@link MessageReceiver}.
 */
@SuppressWarnings("unused")
public class MessageHandler {

    /**
     * Receives  messages, parsed from sensor data, with {@link #parse(byte[])}.
     */
    public interface MessageReceiver {
        /**
         * Callback for receiving {@link Status Status} messages.
         */
        void receive(Status status);

        /**
         * Callback for receiving {@link Location Location} messages.
         */
        void receive(Location location);
    }

    private static boolean initialized = false;

    /***
     * Creates a message handler.
     *
     * @param receiver Receives the parsed messages.
     */
    public MessageHandler(@NonNull MessageReceiver receiver) {
        initialize();
        this.delegate = receiver;
    }

    private synchronized void initialize() {
        if (!initialized) {
            System.loadLibrary("sqsp_wrapper");
            System.loadLibrary("sqtp_wrapper");
            initialized = true;
        }
    }

    private final static String LOG_TAG = "MessageHandler";
    private final MessageReceiver delegate;

    /**
     * Resolves data, received from sensors, into messages.
     *
     * @param data The data from {@link android.bluetooth.BluetoothGattCallback#onCharacteristicChanged(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, byte[])}
     */
    public void parse(@NonNull byte[] data) throws ApiException {
        if (data.length == 0) {
            throw new ApiException("No data to parse");
        }

        if (delegate == null) {
            Log.w(LOG_TAG, "No receiver");
            return;
        }

        SqtpFrameReader_t reader = new SqtpFrameReader_t();
        StableBuffer stableBuffer = new StableBuffer(data);
        SqtpStatus_t initialized = sqtpFrameReaderInit(reader, stableBuffer.array(), data.length);
        assert(SqtpStatus_t.SQTP_STATUS_SUCCESS == initialized);

        while (reader.getStatus() == SqtpStatus_t.SQTP_STATUS_SUCCESS) {
            SqtpSubframeId_t messageId = reader.getSubframe().getId();
            if (SQTP_ID_SITEPOINT_STATUS.equals(messageId)) {
                byte[] subframeBytes = getSubframeBytes(reader, stableBuffer);
                SqspStatus_t sitePointStatus = sqspStatusCopy(subframeBytes);
                delegate.receive(new Status(sitePointStatus));
            } else if (SQTP_ID_SITEPOINT_LLA.equals(messageId)) {
                byte[] subframeBytes = getSubframeBytes(reader, stableBuffer);
                SqspLla_t sitePointLla = sqspLlaCopy(subframeBytes);
                delegate.receive(new Location(sitePointLla));
            } else if ((Arrays.asList(SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG, SQTP_ID_SITEPOINT_RELPOS).contains(messageId))) {
                Log.d(LOG_TAG, "Not yet handled message type: '{" + messageId + "}'");
            } else {
                Log.d(LOG_TAG, "Unsupported message type: '{" + messageId + "}'");
            }
            reader = sqtpFrameReaderNextStruct(reader);
        }

        if (reader.getStatus() != SqtpStatus_t.SQTP_STATUS_FRAME_END) {
            String statusText = reader.getStatus().toString();
            String detail = statusText + ", frame index " + reader.getIndex() + ", subframe id " + reader.getSubframe().getId() + ", payload length " + reader.getSubframe().getLength();
            throw new ApiException("Frame parsing error: " + detail);
        }
    }

    private byte[] getSubframeBytes(SqtpFrameReader_t reader, StableBuffer frameBuffer) {
        int payloadLength = (int)reader.getSubframe().getLength();
        byte[] bytes = new byte[payloadLength];
        // reader index is at the end of the subframe... backup
        int payloadStart = (int)(reader.getIndex() - SqtpCheckValue_tSize() - payloadLength);
        System.arraycopy(frameBuffer.array(), payloadStart, bytes, 0, payloadLength);
        return bytes;
    }
}
