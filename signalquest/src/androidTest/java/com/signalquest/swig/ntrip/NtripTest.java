package com.signalquest.swig.ntrip;

import static com.signalquest.swig.ntrip.ntrip.NTRIP_GetPoll_Buffer;
import static com.signalquest.swig.ntrip.ntrip.NTRIP_Parse;
import static com.signalquest.swig.ntrip.ntrip.getPollIndex;
import static com.signalquest.swig.ntrip.ntrip.getRTCMCRC;
import static com.signalquest.swig.ntrip.ntrip.getRTCMID;
import static com.signalquest.swig.ntrip.ntrip.getRTCMLength;
//import static com.signalquest.swig.ntrip.ntrip.getAccel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.signalquest.api.NtripParser;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testingdocumentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4.class)
public class NtripTest {
    @Test
    public void ntrip_parse_authorization() {

        //  This function tests NTRIP authorization logic.
        //  The test vector is for an Authorization failure.

        System.loadLibrary("ntrip_wrapper");
        String LOG_TAG = "SQ NTRIP";

        // Test that we the following types can be created:
        ByteBuffer myWorkingBuffer = null;
        NTRIP_Parse_Return_t result = null;
        NTRIP_Header_Info_t header = null;
        NTRIP_Parse_Poll_t poll = new NTRIP_Parse_Poll_t();
        Error_Status_t status = Error_Status_t.Error_Status_Unknown;

        // Create our 'myWorkingBuffer'
        myWorkingBuffer = ByteBuffer.allocateDirect(2 * 65536);
        assertTrue(myWorkingBuffer.isDirect());

        // Create our NTRIP Parsing 'context'
        NTRIP_Parse_Context_t parseContext = new NTRIP_Parse_Context_t();
        assertEquals(NTRIP_Parse_Context_State_t.NTRIP_INIT, parseContext.getState());

        // Initialize the NTRIP Parser.
        // NOTE: Don't use 'myWorkingBuffer.array().length' here, it's 7 bytes too long due to
        // Android ByteBuffer.allocateDirect() implementation that adds 7 bytes to all allocations.
        //  This due to the addition of 7 bytes to the data array when .allocateDirect() allocates
        //  memory:
        //  From:
        //  @see https://android.googlesource.com/platform/libcore/+/android-6.0.1_r21/luni/src/main/java/java/nio/ByteBuffer.java#68
        //      // Ensure alignment by 8.
        //      MemoryBlock memoryBlock = MemoryBlock.allocate(capacity + 7);
        //
        NTRIP_Parse_Return_t initialized = ntrip.NTRIP_Parse_Init(parseContext, myWorkingBuffer.array(), myWorkingBuffer.capacity(), null);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_SUCCESS, initialized);

        //========================================================================================
        //  Authorization test vector:
        byte[] data = {72, 84, 84, 80, 47, 49, 46, 49, 32, 52, 48, 48, 32, 66, 97, 100, 32, 82, 101, 113, 117, 101, 115, 116, 13, 10, 13, 10};
        String Display = Arrays.toString(new String(data, StandardCharsets.UTF_8).toCharArray());
        System.out.println(Display);
        //========================================================================================

        result = NTRIP_Parse(parseContext, data, data.length);
//NOTE: Preferred SWIG syntax:        result = NTRIP_Parse(parseContext, data);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);

        // Verify header and Authorization
        header = parseContext.getHeader();
        assertNotNull(header);
        assertFalse(header.getAuthorized());

        String FirstReply = header.getFirst_HTTP_Reply_string();
        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\n", FirstReply);

        // Verify Poll Status, Type and Length
        poll = parseContext.getPoll();
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, poll.getStatus());
        assertEquals(NTRIP_Parse_Types_t.NTRIP_V2_Header_Message, poll.getType());
        assertEquals(28, poll.getLength());

        status = header.getError_Status();
        assertEquals(Error_Status_t.Error_Status_Unknown, status);

        String responseText = header.getFirst_HTTP_Reply_string();
        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\n", responseText);

        // Test Logging message creation:
        Log.w(LOG_TAG, String.format("Auth failed; %s; status/type: %s/%s (%s).", status, poll.getStatus(), poll.getType(), responseText));
        assertEquals(
    "Auth failed; Error_Status_Unknown; status/type: NTRIP_MESSAGE_PENDING/NTRIP_V2_Header_Message (HTTP/1.1 400 Bad Request\r\n\r\n).",
             String.format("Auth failed; %s; status/type: %s/%s (%s).", status, poll.getStatus(), poll.getType(), responseText));

        String reason = header.getShort_Text_string();
        assertEquals("", reason);

        String description = header.getDescription_string();
        assertEquals("", description);
    }

    @Test
    public void ntrip_callable() {

        System.loadLibrary("ntrip_wrapper");

        // Test that we the following types can be created:
        ByteBuffer myWorkingBuffer = null;
        NTRIP_Parse_Return_t result = null;
        NTRIP_Header_Info_t header = null;
        NTRIP_Parse_Poll_t poll = new NTRIP_Parse_Poll_t();
//        Error_Status_t status = Error_Status_t.Error_Status_Unknown;

        // Declare (a reusable) buffer where we will collect our byte[] array RTCM (or possibly
        // 'other') message(s).
        // Note that poll.getType() indicated whether the Poll contains an RTCM message or some
        // other message type.
        byte[] RTCMPollBuffer = null;   // Empty Buffer

        // Create our 'myWorkingBuffer'
        myWorkingBuffer = ByteBuffer.allocateDirect(2 * 65536);
        assertTrue(myWorkingBuffer.isDirect());

        // Create our NTRIP Parsing 'context'
        NTRIP_Parse_Context_t context = new NTRIP_Parse_Context_t();
        assertEquals(NTRIP_Parse_Context_State_t.NTRIP_INIT, context.getState());

        // Initialize the NTRIP Parser.
        // NOTE: Don't use 'myWorkingBuffer.array().length' here, it's 7 bytes too long due to
        // Android ByteBuffer.allocateDirect() implementation that adds 7 bytes to all allocations.
        //  This due to the addition of 7 bytes to the data array when .allocateDirect() allocates
        //  memory:
        //  From:
        //  @see https://android.googlesource.com/platform/libcore/+/android-6.0.1_r21/luni/src/main/java/java/nio/ByteBuffer.java#68
        //      // Ensure alignment by 8.
        //      MemoryBlock memoryBlock = MemoryBlock.allocate(capacity + 7);
        //
        NTRIP_Parse_Return_t initialized = ntrip.NTRIP_Parse_Init(context, myWorkingBuffer.array(), myWorkingBuffer.capacity(), null);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_SUCCESS, initialized);

        // Load Test Data into Parser. Contains a Header and several RTCM messages.
        // (See notes with ntripTestData[] declaration below.)
        result = ntrip.NTRIP_Parse(context, ntripTestData, ntripTestData.length);
//NOTE: Preferred SWIG syntax:        result = ntrip.NTRIP_Parse(context, ntripTestData);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);

        // Verify header and Authorization
        header = context.getHeader();
        assertNotNull(header);
        assertEquals(Error_Status_t.Error_Status_200, header.getError_Status());
        assertTrue(header.getAuthorized());

        // Pull raw header data from the context->WorkingBuffer in 'context' into our
        // 'EntireHeaderBuffer' .
        // Note: Length is poll.getLength() or context.getPoll().getLength() [same thing...]
        //
        // Create a byte[] large enough to hold the data for this Header message (using the Poll API).
        byte[] EntireHeaderBuffer = new byte[(int) context.getPoll().getLength()];   //DEBUG Helper, not typically used or required
        System.arraycopy(myWorkingBuffer.array(), getPollIndex(context), EntireHeaderBuffer, 0, (int) context.getPoll().getLength());

        //---------------------------------------------------------------------------------------
        // For Debugging purposes, let's look at the entire first message in (semi) readable ASCII:

        String Display = Arrays.toString(new String(EntireHeaderBuffer, StandardCharsets.UTF_8).toCharArray());
        assertEquals(3*285, Display.length());
        //---------------------------------------------------------------------------------------

        // NOTE: getFirst_HTTP_Reply_string() only returns the first 64 bytes by design.
        String FirstReply = header.getFirst_HTTP_Reply_string();
        assertEquals(
    "HTTP/1.1 200 OK\r\nNtrip-Version: Ntrip/2.0\r\nConnection: close\r\nT",
             FirstReply);

        // Demonstration of how to fetch 'First_HTTP_Reply_string' or any of the 'xxx_string' values
        // associated with the header:
        // NOTE: getFirst_HTTP_Reply_string() is limited by design to 64 characters including
        // trailing null.
        String myFirst_HTTP_ReplyString = header.getFirst_HTTP_Reply_string();

        // Test Craig's coding that uses a couple of 'xxx_string' values when an error occurs.
        // Note: Using the ntripTestData[] test data , we don't have errors and both
        //       'Short_Text_string' and 'Description_string()' are NULL
        {
            String LOG_TAG = "SQ NTRIP";
            Log.w(LOG_TAG, "Unable to parse RTCM message " + header.getShort_Text_string() + ", " + header.getDescription_string());
        }

        // Test Poll API
        // Verify Poll Status, Type and Length
        poll = context.getPoll();
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, poll.getStatus());
        assertEquals(NTRIP_Parse_Types_t.NTRIP_V2_Header_Message, poll.getType());
        assertEquals(285, poll.getLength());

        //---------------------------------------------------------------------------------------
        // Retrieve RTCM Messages:
        //---------------------------------------------------------------------------------------
        // Index past Header to next Message using NTRIP_Parse_Next_Message().
        // ...then repeat until there are no more messages available...
        //---------------------------------------------------------------------------------------
        // RetrievePollBuffer() is a Java function defined below that returns a byte[] array using
        // System.arraycopy().
        //---------------------------------------------------------------------------------------

//    RTCM_ID = 1012, RTCM Length = 122, Message Length = 128
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(128, RTCMPollBuffer.length);
        assertEquals(1012, getRTCMID(RTCMPollBuffer));
        assertEquals(122, getRTCMLength(RTCMPollBuffer));
        assertEquals(0x4ab2f2, getRTCMCRC(RTCMPollBuffer));

//    RTCM_ID = 1004, RTCM Length = 165, Message Length = 171
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        // Retrieve RTCM Message:
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(171, RTCMPollBuffer.length);
        assertEquals(1004, getRTCMID(RTCMPollBuffer));
        assertEquals(165, getRTCMLength(RTCMPollBuffer));
        assertEquals(16753819, getRTCMCRC(RTCMPollBuffer));

//    RTCM_ID = 1012, RTCM Length = 122, Message Length = 128
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        // Retrieve RTCM Message:
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(128, RTCMPollBuffer.length);
        assertEquals(1012, getRTCMID(RTCMPollBuffer));
        assertEquals(122, getRTCMLength(RTCMPollBuffer));
        assertEquals(4745038, getRTCMCRC(RTCMPollBuffer));

//    RTCM_ID = 1004, RTCM Length = 165, Message Length = 171
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        // Retrieve RTCM Message:
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(171, RTCMPollBuffer.length);
        assertEquals(1004, getRTCMID(RTCMPollBuffer));
        assertEquals(165, getRTCMLength(RTCMPollBuffer));
        assertEquals(6554902, getRTCMCRC(RTCMPollBuffer));

//    RTCM_ID = 1012, RTCM Length = 122, Message Length = 128
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        // Retrieve RTCM Message:
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(128, RTCMPollBuffer.length);
        assertEquals(1012, getRTCMID(RTCMPollBuffer));
        assertEquals(122, getRTCMLength(RTCMPollBuffer));
        assertEquals(7151138, getRTCMCRC(RTCMPollBuffer));

//    RTCM_ID = 1007, RTCM Length = 25, Message Length = 31
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        // Retrieve RTCM Message:
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(31, RTCMPollBuffer.length);
        assertEquals(1007, getRTCMID(RTCMPollBuffer));
        assertEquals(25, getRTCMLength(RTCMPollBuffer));
        assertEquals(12208863, getRTCMCRC(RTCMPollBuffer));

//    RTCM_ID = 1033, RTCM Length = 72, Message Length = 78
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        // Retrieve RTCM Message:
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(78, RTCMPollBuffer.length);
        assertEquals(1033, getRTCMID(RTCMPollBuffer));
        assertEquals(72, getRTCMLength(RTCMPollBuffer));
        assertEquals(4102247, getRTCMCRC(RTCMPollBuffer));

// All done!  (Should be no more messages)
        result = ntrip.NTRIP_Parse_Next_Message(context);
        assertEquals(NTRIP_Parse_Return_t.NTRIP_NO_MESSAGE_PENDING, result);
        assertEquals(NTRIP_Parse_Types_t.NTRIP_RTCM_Message, poll.getType());
        // NOTE: We receive a 'NTRIP_RTCM_Message' Type by default here, but it is meaningless
        // since we also received a 'NTRIP_NO_MESSAGE_PENDING' status.
        // Since we received a NTRIP_NO_MESSAGE_PENDING status here, and you wouldn't ordinarily
        // try to retrieve a PollBuffer, but we're going to test NTRIP_GetPoll_Buffer() for good behavior!
        // Retrieve RTCM Message:
        RTCMPollBuffer = null;  //Clear prior to use since we're reusing it to read all of these messages.
        RTCMPollBuffer = RetrievePollBuffer(context, myWorkingBuffer.array());
        assertEquals(0, RTCMPollBuffer.length);
        assertEquals(-1, getRTCMID(RTCMPollBuffer));
        assertEquals(-1, getRTCMLength(RTCMPollBuffer));
        assertEquals(-1, getRTCMCRC(RTCMPollBuffer));
    }

    public byte[] RetrievePollBuffer(NTRIP_Parse_Context_t Context, byte[] myWorkingBufferArray ) {
//TODO: Consider more hardening: argument qualification and Exception Handling.
        byte[] JavaByteArray = new byte[(int) Context.getPoll().getLength()];
        System.arraycopy(myWorkingBufferArray, getPollIndex(Context), JavaByteArray, 0, (int) Context.getPoll().getLength());
        return (JavaByteArray);
    }

//  ----------------
//  NTRIP Test Data:
//  ----------------
//
//    NTRIP_V2_Header_Message: ntripTestData[] Buffer contains:
//
//  [HTTP/1.1 200 OK
//    Ntrip-Version: Ntrip/2.0
//    Connection: close
//    Transfer-Encoding: chunked
//    Server: NTRIP Trimble Ntrip Caster 4.7
//    Ntrip-Flags: st_match,st_strict,rtsp,plain_rtp
//    Content-Type: gnss/data
//    Date: Fri, 04 Aug 2023 15:02:09 UTC
//    Cache-Control: no-store
//    Pragma: no-cache
//  ] Length = 285
//
//    RTCM_ID = 1012, RTCM Length = 122, Message Length = 128
//    RTCM_ID = 1004, RTCM Length = 165, Message Length = 171
//    RTCM_ID = 1012, RTCM Length = 122, Message Length = 128
//    RTCM_ID = 1004, RTCM Length = 165, Message Length = 171
//    RTCM_ID = 1012, RTCM Length = 122, Message Length = 128
//    RTCM_ID = 1007, RTCM Length = 25, Message Length = 31
//    RTCM_ID = 1033, RTCM Length = 72, Message Length = 78

    private byte[] ntripTestData = {
            (byte) 0x48, (byte) 0x54, (byte) 0x54, (byte) 0x50, (byte) 0x2f, (byte) 0x31, (byte) 0x2e, (byte) 0x31, (byte) 0x20, (byte) 0x32, (byte) 0x30, (byte) 0x30, (byte) 0x20, (byte) 0x4f, (byte) 0x4b, (byte) 0x0d,
            (byte) 0x0a, (byte) 0x4e, (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x70, (byte) 0x2d, (byte) 0x56, (byte) 0x65, (byte) 0x72, (byte) 0x73, (byte) 0x69, (byte) 0x6f, (byte) 0x6e, (byte) 0x3a, (byte) 0x20,
            (byte) 0x4e, (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x70, (byte) 0x2f, (byte) 0x32, (byte) 0x2e, (byte) 0x30, (byte) 0x0d, (byte) 0x0a, (byte) 0x43, (byte) 0x6f, (byte) 0x6e, (byte) 0x6e, (byte) 0x65,
            (byte) 0x63, (byte) 0x74, (byte) 0x69, (byte) 0x6f, (byte) 0x6e, (byte) 0x3a, (byte) 0x20, (byte) 0x63, (byte) 0x6c, (byte) 0x6f, (byte) 0x73, (byte) 0x65, (byte) 0x0d, (byte) 0x0a, (byte) 0x54, (byte) 0x72,
            (byte) 0x61, (byte) 0x6e, (byte) 0x73, (byte) 0x66, (byte) 0x65, (byte) 0x72, (byte) 0x2d, (byte) 0x45, (byte) 0x6e, (byte) 0x63, (byte) 0x6f, (byte) 0x64, (byte) 0x69, (byte) 0x6e, (byte) 0x67, (byte) 0x3a,
            (byte) 0x20, (byte) 0x63, (byte) 0x68, (byte) 0x75, (byte) 0x6e, (byte) 0x6b, (byte) 0x65, (byte) 0x64, (byte) 0x0d, (byte) 0x0a, (byte) 0x53, (byte) 0x65, (byte) 0x72, (byte) 0x76, (byte) 0x65, (byte) 0x72,
            (byte) 0x3a, (byte) 0x20, (byte) 0x4e, (byte) 0x54, (byte) 0x52, (byte) 0x49, (byte) 0x50, (byte) 0x20, (byte) 0x54, (byte) 0x72, (byte) 0x69, (byte) 0x6d, (byte) 0x62, (byte) 0x6c, (byte) 0x65, (byte) 0x20,
            (byte) 0x4e, (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x70, (byte) 0x20, (byte) 0x43, (byte) 0x61, (byte) 0x73, (byte) 0x74, (byte) 0x65, (byte) 0x72, (byte) 0x20, (byte) 0x34, (byte) 0x2e, (byte) 0x37,
            (byte) 0x0d, (byte) 0x0a, (byte) 0x4e, (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x70, (byte) 0x2d, (byte) 0x46, (byte) 0x6c, (byte) 0x61, (byte) 0x67, (byte) 0x73, (byte) 0x3a, (byte) 0x20, (byte) 0x73,
            (byte) 0x74, (byte) 0x5f, (byte) 0x6d, (byte) 0x61, (byte) 0x74, (byte) 0x63, (byte) 0x68, (byte) 0x2c, (byte) 0x73, (byte) 0x74, (byte) 0x5f, (byte) 0x73, (byte) 0x74, (byte) 0x72, (byte) 0x69, (byte) 0x63,
            (byte) 0x74, (byte) 0x2c, (byte) 0x72, (byte) 0x74, (byte) 0x73, (byte) 0x70, (byte) 0x2c, (byte) 0x70, (byte) 0x6c, (byte) 0x61, (byte) 0x69, (byte) 0x6e, (byte) 0x5f, (byte) 0x72, (byte) 0x74, (byte) 0x70,
            (byte) 0x0d, (byte) 0x0a, (byte) 0x43, (byte) 0x6f, (byte) 0x6e, (byte) 0x74, (byte) 0x65, (byte) 0x6e, (byte) 0x74, (byte) 0x2d, (byte) 0x54, (byte) 0x79, (byte) 0x70, (byte) 0x65, (byte) 0x3a, (byte) 0x20,
            (byte) 0x67, (byte) 0x6e, (byte) 0x73, (byte) 0x73, (byte) 0x2f, (byte) 0x64, (byte) 0x61, (byte) 0x74, (byte) 0x61, (byte) 0x0d, (byte) 0x0a, (byte) 0x44, (byte) 0x61, (byte) 0x74, (byte) 0x65, (byte) 0x3a,
            (byte) 0x20, (byte) 0x46, (byte) 0x72, (byte) 0x69, (byte) 0x2c, (byte) 0x20, (byte) 0x30, (byte) 0x34, (byte) 0x20, (byte) 0x41, (byte) 0x75, (byte) 0x67, (byte) 0x20, (byte) 0x32, (byte) 0x30, (byte) 0x32,
            (byte) 0x33, (byte) 0x20, (byte) 0x31, (byte) 0x35, (byte) 0x3a, (byte) 0x30, (byte) 0x32, (byte) 0x3a, (byte) 0x30, (byte) 0x39, (byte) 0x20, (byte) 0x55, (byte) 0x54, (byte) 0x43, (byte) 0x0d, (byte) 0x0a,
            (byte) 0x43, (byte) 0x61, (byte) 0x63, (byte) 0x68, (byte) 0x65, (byte) 0x2d, (byte) 0x43, (byte) 0x6f, (byte) 0x6e, (byte) 0x74, (byte) 0x72, (byte) 0x6f, (byte) 0x6c, (byte) 0x3a, (byte) 0x20, (byte) 0x6e,
            (byte) 0x6f, (byte) 0x2d, (byte) 0x73, (byte) 0x74, (byte) 0x6f, (byte) 0x72, (byte) 0x65, (byte) 0x0d, (byte) 0x0a, (byte) 0x50, (byte) 0x72, (byte) 0x61, (byte) 0x67, (byte) 0x6d, (byte) 0x61, (byte) 0x3a,
            (byte) 0x20, (byte) 0x6e, (byte) 0x6f, (byte) 0x2d, (byte) 0x63, (byte) 0x61, (byte) 0x63, (byte) 0x68, (byte) 0x65, (byte) 0x0d, (byte) 0x0a, (byte) 0x0d, (byte) 0x0a, /*(byte) 0x00, (byte) 0x00, (byte) 0x00,*/
            (byte) 0x38, (byte) 0x30, (byte) 0x0d, (byte) 0x0a, (byte) 0xd3, (byte) 0x00, (byte) 0x7a, (byte) 0x3f, (byte) 0x40, (byte) 0x05, (byte) 0x7b, (byte) 0xd8, (byte) 0x1a, (byte) 0x03, (byte) 0x80, (byte) 0x41,
            (byte) 0xaf, (byte) 0x08, (byte) 0xb7, (byte) 0x01, (byte) 0x27, (byte) 0xfb, (byte) 0xfa, (byte) 0x3c, (byte) 0x04, (byte) 0x02, (byte) 0xf0, (byte) 0x60, (byte) 0x80, (byte) 0xff, (byte) 0x66, (byte) 0x19,
            (byte) 0x96, (byte) 0x06, (byte) 0x49, (byte) 0x50, (byte) 0x74, (byte) 0xb4, (byte) 0xfe, (byte) 0x83, (byte) 0x09, (byte) 0xff, (byte) 0xec, (byte) 0x27, (byte) 0x09, (byte) 0x7f, (byte) 0xe2, (byte) 0x08,
            (byte) 0x6e, (byte) 0x3a, (byte) 0xc9, (byte) 0xc8, (byte) 0x17, (byte) 0x2a, (byte) 0xbf, (byte) 0xa4, (byte) 0xb9, (byte) 0x40, (byte) 0x9a, (byte) 0x07, (byte) 0x82, (byte) 0x5f, (byte) 0xf3, (byte) 0xc4,
            (byte) 0x8a, (byte) 0x71, (byte) 0xb7, (byte) 0x5e, (byte) 0xfe, (byte) 0x14, (byte) 0xaf, (byte) 0xe9, (byte) 0xac, (byte) 0x90, (byte) 0x1e, (byte) 0x3f, (byte) 0x63, (byte) 0xfb, (byte) 0xfd, (byte) 0x02,
            (byte) 0x25, (byte) 0xf0, (byte) 0x10, (byte) 0xf1, (byte) 0x3f, (byte) 0x14, (byte) 0xf7, (byte) 0xfa, (byte) 0x3b, (byte) 0xa4, (byte) 0x08, (byte) 0x6f, (byte) 0xaa, (byte) 0x15, (byte) 0xff, (byte) 0x6c,
            (byte) 0x90, (byte) 0x91, (byte) 0xbf, (byte) 0x4d, (byte) 0x20, (byte) 0x15, (byte) 0xe7, (byte) 0xfe, (byte) 0x83, (byte) 0x0d, (byte) 0xff, (byte) 0xa4, (byte) 0x07, (byte) 0xe5, (byte) 0xbf, (byte) 0xdf,
            (byte) 0xa6, (byte) 0x51, (byte) 0x25, (byte) 0x9b, (byte) 0x80, (byte) 0x09, (byte) 0xd7, (byte) 0x3f, (byte) 0xa3, (byte) 0x9c, (byte) 0x40, (byte) 0x01, (byte) 0x03, (byte) 0x4a, (byte) 0x2f, (byte) 0xf5,
            (byte) 0x60, (byte) 0x4a, (byte) 0xb2, (byte) 0xf2, (byte) 0x0d, (byte) 0x0a, (byte) 0x41, (byte) 0x42, (byte) 0x0d, (byte) 0x0a, (byte) 0xd3, (byte) 0x00, (byte) 0xa5, (byte) 0x3e, (byte) 0xc0, (byte) 0x05,
            (byte) 0x73, (byte) 0xe8, (byte) 0x2e, (byte) 0x22, (byte) 0xa0, (byte) 0x10, (byte) 0xbb, (byte) 0x79, (byte) 0x56, (byte) 0x04, (byte) 0xda, (byte) 0xbf, (byte) 0xd4, (byte) 0xe4, (byte) 0xf0, (byte) 0x50,
            (byte) 0x80, (byte) 0xcb, (byte) 0x7b, (byte) 0xfd, (byte) 0x18, (byte) 0xab, (byte) 0x2d, (byte) 0x13, (byte) 0x70, (byte) 0x78, (byte) 0x92, (byte) 0xfe, (byte) 0x91, (byte) 0x77, (byte) 0x80, (byte) 0xfe,
            (byte) 0x13, (byte) 0xa2, (byte) 0x3f, (byte) 0xec, (byte) 0x06, (byte) 0x6e, (byte) 0x15, (byte) 0x5e, (byte) 0x7e, (byte) 0xff, (byte) 0x67, (byte) 0xf4, (byte) 0x8b, (byte) 0x7c, (byte) 0x11, (byte) 0x9f,
            (byte) 0xd4, (byte) 0x4c, (byte) 0xff, (byte) 0x7c, (byte) 0x49, (byte) 0x37, (byte) 0x08, (byte) 0xd8, (byte) 0x12, (byte) 0x24, (byte) 0x7f, (byte) 0xa6, (byte) 0xd4, (byte) 0x60, (byte) 0x95, (byte) 0x82,
            (byte) 0xf4, (byte) 0x6f, (byte) 0xfa, (byte) 0xe2, (byte) 0xc8, (byte) 0xdb, (byte) 0xe1, (byte) 0x80, (byte) 0x59, (byte) 0xef, (byte) 0xfd, (byte) 0x12, (byte) 0xeb, (byte) 0x00, (byte) 0x88, (byte) 0x0f,
            (byte) 0x67, (byte) 0x3f, (byte) 0xe9, (byte) 0x18, (byte) 0xa0, (byte) 0x22, (byte) 0x63, (byte) 0xfd, (byte) 0xe7, (byte) 0x1f, (byte) 0xe9, (byte) 0x76, (byte) 0x58, (byte) 0x18, (byte) 0xdf, (byte) 0xa6,
            (byte) 0x63, (byte) 0xfe, (byte) 0xbd, (byte) 0x35, (byte) 0x97, (byte) 0xca, (byte) 0x57, (byte) 0xc1, (byte) 0x7d, (byte) 0x7f, (byte) 0x4d, (byte) 0xaf, (byte) 0xc0, (byte) 0xfe, (byte) 0xf5, (byte) 0x9a,
            (byte) 0x0f, (byte) 0xef, (byte) 0x0a, (byte) 0x2f, (byte) 0x68, (byte) 0x6c, (byte) 0x41, (byte) 0x86, (byte) 0x57, (byte) 0xfa, (byte) 0x1d, (byte) 0xae, (byte) 0x01, (byte) 0x50, (byte) 0x40, (byte) 0x74,
            (byte) 0xff, (byte) 0xa2, (byte) 0x65, (byte) 0xc3, (byte) 0x72, (byte) 0x00, (byte) 0x00, (byte) 0xa5, (byte) 0x7f, (byte) 0xd2, (byte) 0xea, (byte) 0xb0, (byte) 0x4d, (byte) 0x00, (byte) 0x1d, (byte) 0xd7,
            (byte) 0xfd, (byte) 0x73, (byte) 0xa0, (byte) 0x0a, (byte) 0xbd, (byte) 0x00, (byte) 0x10, (byte) 0x66, (byte) 0xfe, (byte) 0xa3, (byte) 0x49, (byte) 0x81, (byte) 0xe6, (byte) 0x02, (byte) 0xb6, (byte) 0x7f,
            (byte) 0xe7, (byte) 0xc0, (byte) 0xff, (byte) 0xa4, (byte) 0x9b, (byte) 0x0d, (byte) 0x0a, (byte) 0x38, (byte) 0x30, (byte) 0x0d, (byte) 0x0a, (byte) 0xd3, (byte) 0x00, (byte) 0x7a, (byte) 0x3f, (byte) 0x40,
            (byte) 0x05, (byte) 0x7b, (byte) 0xd8, (byte) 0x97, (byte) 0x03, (byte) 0x80, (byte) 0x41, (byte) 0xaf, (byte) 0x21, (byte) 0xb1, (byte) 0x41, (byte) 0x29, (byte) 0xff, (byte) 0xfa, (byte) 0x3c, (byte) 0x04,
            (byte) 0x02, (byte) 0x80, (byte) 0x60, (byte) 0xf9, (byte) 0xff, (byte) 0x68, (byte) 0x19, (byte) 0x96, (byte) 0x03, (byte) 0x3c, (byte) 0x20, (byte) 0x75, (byte) 0x42, (byte) 0xfe, (byte) 0x83, (byte) 0x09,
            (byte) 0xff, (byte) 0xf4, (byte) 0x27, (byte) 0x2e, (byte) 0xbf, (byte) 0xe2, (byte) 0x08, (byte) 0x6e, (byte) 0x38, (byte) 0x54, (byte) 0xf0, (byte) 0x17, (byte) 0x80, (byte) 0xff, (byte) 0xa4, (byte) 0xb8,
            (byte) 0x40, (byte) 0x9e, (byte) 0x07, (byte) 0x97, (byte) 0xef, (byte) 0xf4, (byte) 0x04, (byte) 0x8a, (byte) 0x71, (byte) 0xf2, (byte) 0xbd, (byte) 0xfd, (byte) 0xf5, (byte) 0x8f, (byte) 0xe9, (byte) 0xac,
            (byte) 0x90, (byte) 0x1a, (byte) 0xbf, (byte) 0x5c, (byte) 0x6f, (byte) 0xfc, (byte) 0xf2, (byte) 0x25, (byte) 0xf0, (byte) 0x28, (byte) 0x1a, (byte) 0xff, (byte) 0x0c, (byte) 0x13, (byte) 0xfa, (byte) 0x3b,
            (byte) 0xb4, (byte) 0x07, (byte) 0x2f, (byte) 0xa7, (byte) 0xdf, (byte) 0xff, (byte) 0x6e, (byte) 0x90, (byte) 0x91, (byte) 0xbe, (byte) 0xe9, (byte) 0xd0, (byte) 0x15, (byte) 0xd0, (byte) 0xfe, (byte) 0x83,
            (byte) 0x0d, (byte) 0xff, (byte) 0xb0, (byte) 0x07, (byte) 0xe0, (byte) 0xff, (byte) 0xe0, (byte) 0x26, (byte) 0x51, (byte) 0x24, (byte) 0x1f, (byte) 0xe4, (byte) 0x09, (byte) 0x76, (byte) 0x3f, (byte) 0xa3,
            (byte) 0x9a, (byte) 0x7f, (byte) 0xf2, (byte) 0x03, (byte) 0x31, (byte) 0xcf, (byte) 0xf5, (byte) 0x40, (byte) 0x48, (byte) 0x67, (byte) 0x4e, (byte) 0x0d, (byte) 0x0a, (byte) 0x41, (byte) 0x42, (byte) 0x0d,
            (byte) 0x0a, (byte) 0xd3, (byte) 0x00, (byte) 0xa5, (byte) 0x3e, (byte) 0xc0, (byte) 0x05, (byte) 0x73, (byte) 0xe8, (byte) 0x3d, (byte) 0xc2, (byte) 0xa0, (byte) 0x10, (byte) 0xbc, (byte) 0x2c, (byte) 0x94,
            (byte) 0x04, (byte) 0xb3, (byte) 0x1f, (byte) 0xd4, (byte) 0xe4, (byte) 0xb0, (byte) 0x4f, (byte) 0x40, (byte) 0xc6, (byte) 0x9f, (byte) 0xfd, (byte) 0x10, (byte) 0xab, (byte) 0x26, (byte) 0x9c, (byte) 0xd0,
            (byte) 0x79, (byte) 0x27, (byte) 0xfe, (byte) 0x91, (byte) 0x75, (byte) 0x81, (byte) 0x0a, (byte) 0x13, (byte) 0xb5, (byte) 0x1f, (byte) 0xec, (byte) 0x46, (byte) 0x6e, (byte) 0x3c, (byte) 0xb1, (byte) 0x7e,
            (byte) 0xf4, (byte) 0xe7, (byte) 0xf4, (byte) 0x8b, (byte) 0x5c, (byte) 0x11, (byte) 0x3f, (byte) 0xd2, (byte) 0xfd, (byte) 0xff, (byte) 0x80, (byte) 0x49, (byte) 0x37, (byte) 0xc2, (byte) 0xf0, (byte) 0x12,
            (byte) 0xba, (byte) 0x7f, (byte) 0xa6, (byte) 0xd4, (byte) 0xe0, (byte) 0x9c, (byte) 0x83, (byte) 0x07, (byte) 0x47, (byte) 0xfa, (byte) 0xd2, (byte) 0xc8, (byte) 0xdb, (byte) 0xf0, (byte) 0x60, (byte) 0x57,
            (byte) 0x19, (byte) 0xfd, (byte) 0x12, (byte) 0xe7, (byte) 0x00, (byte) 0x7c, (byte) 0x0f, (byte) 0x09, (byte) 0xff, (byte) 0xe9, (byte) 0x18, (byte) 0xa0, (byte) 0x80, (byte) 0x11, (byte) 0xfd, (byte) 0xd5,
            (byte) 0xaf, (byte) 0xe9, (byte) 0x76, (byte) 0x58, (byte) 0x18, (byte) 0x5f, (byte) 0xa4, (byte) 0x2d, (byte) 0xfe, (byte) 0xbd, (byte) 0x35, (byte) 0x9b, (byte) 0xfd, (byte) 0x1f, (byte) 0xc0, (byte) 0x54,
            (byte) 0x7f, (byte) 0x4d, (byte) 0xb0, (byte) 0xc0, (byte) 0xef, (byte) 0xf5, (byte) 0x74, (byte) 0x7f, (byte) 0xee, (byte) 0x6a, (byte) 0x2f, (byte) 0x5f, (byte) 0xcc, (byte) 0x01, (byte) 0x85, (byte) 0x63,
            (byte) 0xfa, (byte) 0x1d, (byte) 0xa6, (byte) 0x01, (byte) 0x18, (byte) 0x40, (byte) 0x57, (byte) 0x7f, (byte) 0xa0, (byte) 0x65, (byte) 0xc3, (byte) 0x9c, (byte) 0x14, (byte) 0x00, (byte) 0x96, (byte) 0x7f,
            (byte) 0xd2, (byte) 0xea, (byte) 0xf0, (byte) 0x4b, (byte) 0xc0, (byte) 0x1b, (byte) 0xe3, (byte) 0xfd, (byte) 0x7b, (byte) 0xa0, (byte) 0x02, (byte) 0xa1, (byte) 0x80, (byte) 0x10, (byte) 0x57, (byte) 0xfe,
            (byte) 0xa3, (byte) 0x45, (byte) 0x81, (byte) 0xee, (byte) 0x02, (byte) 0xb2, (byte) 0x1f, (byte) 0xe7, (byte) 0xc0, (byte) 0x64, (byte) 0x05, (byte) 0x16, (byte) 0x0d, (byte) 0x0a, (byte) 0x45, (byte) 0x44,
            (byte) 0x0d, (byte) 0x0a, (byte) 0xd3, (byte) 0x00, (byte) 0x7a, (byte) 0x3f, (byte) 0x40, (byte) 0x05, (byte) 0x7b, (byte) 0xd9, (byte) 0x14, (byte) 0x03, (byte) 0x80, (byte) 0x41, (byte) 0xaf, (byte) 0x3a,
            (byte) 0xb0, (byte) 0x01, (byte) 0x23, (byte) 0x17, (byte) 0xfa, (byte) 0x3c, (byte) 0x24, (byte) 0x02, (byte) 0x70, (byte) 0x5f, (byte) 0x3f, (byte) 0xff, (byte) 0x64, (byte) 0x19, (byte) 0x96, (byte) 0x00,
            (byte) 0x2f, (byte) 0x20, (byte) 0x76, (byte) 0x50, (byte) 0xfe, (byte) 0x83, (byte) 0x09, (byte) 0x00, (byte) 0x10, (byte) 0x27, (byte) 0x70, (byte) 0x3f, (byte) 0xe3, (byte) 0x08, (byte) 0x6e, (byte) 0x35,
            (byte) 0xe0, (byte) 0x24, (byte) 0x17, (byte) 0xc1, (byte) 0xff, (byte) 0xa4, (byte) 0xb7, (byte) 0x40, (byte) 0xa9, (byte) 0x07, (byte) 0xa7, (byte) 0xbf, (byte) 0xf3, (byte) 0xc4, (byte) 0x8a, (byte) 0x72,
            (byte) 0x2e, (byte) 0x0a, (byte) 0xfe, (byte) 0x0f, (byte) 0x7f, (byte) 0xe9, (byte) 0xac, (byte) 0x10, (byte) 0x1e, (byte) 0x7f, (byte) 0x62, (byte) 0xc7, (byte) 0xfc, (byte) 0xfa, (byte) 0x25, (byte) 0xf0,
            (byte) 0x3f, (byte) 0x42, (byte) 0xbf, (byte) 0x08, (byte) 0xcf, (byte) 0xfa, (byte) 0x3b, (byte) 0xb4, (byte) 0x06, (byte) 0x5f, (byte) 0xa7, (byte) 0x05, (byte) 0xff, (byte) 0x70, (byte) 0x90, (byte) 0x91,
            (byte) 0xbe, (byte) 0x85, (byte) 0xf0, (byte) 0x17, (byte) 0x71, (byte) 0xfe, (byte) 0x83, (byte) 0x09, (byte) 0xff, (byte) 0xd4, (byte) 0x08, (byte) 0x49, (byte) 0xbf, (byte) 0xdf, (byte) 0xa6, (byte) 0x51,
            (byte) 0x22, (byte) 0xa4, (byte) 0x04, (byte) 0x09, (byte) 0xbb, (byte) 0x3f, (byte) 0xa3, (byte) 0x9b, (byte) 0x7f, (byte) 0xfe, (byte) 0x03, (byte) 0x42, (byte) 0xff, (byte) 0xf5, (byte) 0x40, (byte) 0x6d,
            (byte) 0x1e, (byte) 0x22, (byte) 0xd3, (byte) 0x00, (byte) 0x19, (byte) 0x3e, (byte) 0xf0, (byte) 0x05, (byte) 0x14, (byte) 0x41, (byte) 0x44, (byte) 0x56, (byte) 0x4e, (byte) 0x55, (byte) 0x4c, (byte) 0x4c,
            (byte) 0x41, (byte) 0x4e, (byte) 0x54, (byte) 0x45, (byte) 0x4e, (byte) 0x4e, (byte) 0x41, (byte) 0x20, (byte) 0x20, (byte) 0x4e, (byte) 0x4f, (byte) 0x4e, (byte) 0x45, (byte) 0x00, (byte) 0xba, (byte) 0x4a,
            (byte) 0xdf, (byte) 0xd3, (byte) 0x00, (byte) 0x48, (byte) 0x40, (byte) 0x90, (byte) 0x05, (byte) 0x14, (byte) 0x41, (byte) 0x44, (byte) 0x56, (byte) 0x4e, (byte) 0x55, (byte) 0x4c, (byte) 0x4c, (byte) 0x41,
            (byte) 0x4e, (byte) 0x54, (byte) 0x45, (byte) 0x4e, (byte) 0x4e, (byte) 0x41, (byte) 0x20, (byte) 0x20, (byte) 0x4e, (byte) 0x4f, (byte) 0x4e, (byte) 0x45, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x54,
            (byte) 0x52, (byte) 0x49, (byte) 0x4d, (byte) 0x42, (byte) 0x4c, (byte) 0x45, (byte) 0x20, (byte) 0x41, (byte) 0x4c, (byte) 0x4c, (byte) 0x4f, (byte) 0x59, (byte) 0x14, (byte) 0x4e, (byte) 0x61, (byte) 0x76,
            (byte) 0x20, (byte) 0x36, (byte) 0x2e, (byte) 0x31, (byte) 0x36, (byte) 0x20, (byte) 0x2f, (byte) 0x20, (byte) 0x42, (byte) 0x6f, (byte) 0x6f, (byte) 0x74, (byte) 0x20, (byte) 0x35, (byte) 0x2e, (byte) 0x35,
            (byte) 0x36, (byte) 0x0a, (byte) 0x36, (byte) 0x30, (byte) 0x32, (byte) 0x34, (byte) 0x52, (byte) 0x34, (byte) 0x30, (byte) 0x30, (byte) 0x37, (byte) 0x39, (byte) 0x3e, (byte) 0x98, (byte) 0x67, (byte) 0x0d,
            (byte) 0x0a, (byte) 0x41, (byte) 0x42, (byte) 0x0d, (byte) 0x0a, (byte) 0xd3, (byte) 0x00, (byte) 0xa5, (byte) 0x3e, (byte) 0xc0, (byte) 0x05, (byte) 0x73, (byte) 0xe8, (byte) 0x4d, (byte) 0x62, (byte) 0xa0,
            (byte) 0x10, (byte) 0xbc, (byte) 0xdf, (byte) 0xfe, (byte) 0x04, (byte) 0x36, (byte) 0x1f, (byte) 0xd4, (byte) 0xe5, (byte) 0xb0, (byte) 0x4b, (byte) 0x00, (byte) 0xb6, (byte) 0xff, (byte) 0xfd, (byte) 0x00,
            (byte) 0xab, (byte) 0x20, (byte) 0x26, (byte) 0x90, (byte) 0x79, (byte) 0x70, (byte) 0xfe, (byte) 0x91, (byte) 0x79, (byte) 0x81, (byte) 0x10, (byte) 0x13, (byte) 0xbe, (byte) 0xff, (byte) 0xec, (byte) 0x86,
            (byte) 0x6e, (byte) 0x63, (byte) 0xfc, (byte) 0xfe, (byte) 0xff, (byte) 0xef, (byte) 0xf4, (byte) 0x8b, (byte) 0x6c, (byte) 0x11, (byte) 0xbf, (byte) 0xd4, (byte) 0x5d, (byte) 0xff, (byte) 0x80, (byte) 0x49,
            (byte) 0x38, (byte) 0x7d, (byte) 0x04, (byte) 0x13, (byte) 0x9c, (byte) 0x7f, (byte) 0xa6, (byte) 0xd4, (byte) 0xe0, (byte) 0xa8, (byte) 0x83, (byte) 0x23, (byte) 0x8f, (byte) 0xfa, (byte) 0xc2, (byte) 0xc8,
            (byte) 0xdb, (byte) 0xff, (byte) 0x00, (byte) 0x55, (byte) 0xeb, (byte) 0xfd, (byte) 0x12, (byte) 0xeb, (byte) 0x00, (byte) 0x88, (byte) 0x0e, (byte) 0xe7, (byte) 0x3f, (byte) 0xea, (byte) 0x18, (byte) 0xa0,
            (byte) 0xdd, (byte) 0xb8, (byte) 0xfd, (byte) 0xe1, (byte) 0x9f, (byte) 0xe9, (byte) 0x76, (byte) 0x58, (byte) 0x19, (byte) 0x3f, (byte) 0xa5, (byte) 0xa9, (byte) 0xfe, (byte) 0xc1, (byte) 0x35, (byte) 0xa0,
            (byte) 0x2f, (byte) 0x5f, (byte) 0xc0, (byte) 0xb4, (byte) 0xff, (byte) 0x4d, (byte) 0xb0, (byte) 0xc0, (byte) 0xf6, (byte) 0xf5, (byte) 0x80, (byte) 0x4f, (byte) 0xee, (byte) 0xea, (byte) 0x2f, (byte) 0x57,
            (byte) 0x2b, (byte) 0x41, (byte) 0x88, (byte) 0xe7, (byte) 0xfa, (byte) 0x1d, (byte) 0x9e, (byte) 0x01, (byte) 0x40, (byte) 0x40, (byte) 0xc6, (byte) 0x7f, (byte) 0xa3, (byte) 0x65, (byte) 0xc3, (byte) 0xc6,
            (byte) 0x2e, (byte) 0x00, (byte) 0x98, (byte) 0x7f, (byte) 0xd2, (byte) 0xea, (byte) 0xf0, (byte) 0x4d, (byte) 0xc0, (byte) 0x1c, (byte) 0x27, (byte) 0xfd, (byte) 0x73, (byte) 0xae, (byte) 0x46, (byte) 0x19,
            (byte) 0xb0, (byte) 0x0f, (byte) 0xd7, (byte) 0xfe, (byte) 0xa1, (byte) 0x47, (byte) 0x81, (byte) 0xe6, (byte) 0x02, (byte) 0xa3, (byte) 0xdf, (byte) 0xe7, (byte) 0xc0};
}
