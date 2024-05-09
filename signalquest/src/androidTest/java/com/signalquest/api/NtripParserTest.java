package com.signalquest.api;

import static org.junit.Assert.assertArrayEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
public class NtripParserTest {

    @Test public void testReceiver() throws NtripParser.AuthorizationFailure {
        NtripParser parser = new NtripParser(rtcmMessage -> {
            parsedResults[parsedIndex] = rtcmMessage;
            parsedIndex++;
        });
        parser.parseAuthorized(icy200Ok);
        parser.parseRtcm(combinedInput);
        for (int i = 0; i < inputMessages.length; i++) {
            assertArrayEquals(inputMessages[i], parsedResults[i]);
        }
    }
    byte[] icy200Ok = new byte[]{0x49, 0x43, 0x59, 0x20, 0x32, 0x30, 0x30, 0x20, 0x4F, 0x4B, 0x0D, 0x0A, 0x0D, 0x0A};
    byte[][] inputMessages = {
        {
            (byte)0xD3, 0x00, 0x6A, 0x3F, 0x40, 0x05, 0x04, (byte)0xB7, 0x08, 0x03, 0x00, 0x24, 0x6C,
            (byte)0xD1, 0x37, (byte)0x84, 0x19, (byte)0x9B, (byte)0xFA, 0x1B, 0x64, 0x02, 0x20, (byte)0xD5,
            (byte)0xF9, (byte)0xFF, 0x48, 0x10, 0x69, (byte)0x9F, 0x65, (byte)0xD1, 0x0F, (byte)0xAC,
            (byte)0xFE, (byte)0x83, 0x21, (byte)0xFF, (byte)0xA4, 0x4E, (byte)0x86, 0x3F, (byte)0xE0,
            0x06, 0x64, (byte)0x93, 0x33, 0x08, 0x1A, (byte)0xC4, 0x3F, (byte)0xA5, (byte)0xAF, 0x40,
            (byte)0xDE, 0x08, (byte)0xE2, 0x6F, (byte)0xF4, (byte)0xA8, (byte)0x96, (byte)0xBA, 0x24,
            0x14, 0x06, (byte)0xA0, 0x7F, (byte)0xE8, 0x31, (byte)0x9F, (byte)0xF5, (byte)0xC2, 0x34,
            (byte)0x8F, (byte)0xFE, 0x02, 0x42, 0x32, (byte)0xE0, 0x1C, (byte)0x80, (byte)0x99, 0x7B,
            (byte)0xFA, 0x5A, (byte)0xF4, 0x05, 0x30, 0x34, 0x32, (byte)0xFF, 0x4C, (byte)0xC1, 0x24,
            0x32, (byte)0x92, 0x30, 0x7A, 0x3B, (byte)0xFE, (byte)0x8B, 0x0D, 0x00, 0x7C, 0x29, 0x0A,
            (byte)0xFF, (byte)0xDC, 0x00, 0x5A, (byte)0xAA, 0x5B
        }, {
            (byte)0xD3, 0x00, 0x19, 0x3E, (byte)0xF0,
            0x05, 0x14, 0x41, 0x44, 0x56, 0x4E, 0x55, 0x4C, 0x4C, 0x41, 0x4E, 0x54, 0x45, 0x4E, 0x4E,
            0x41, 0x20, 0x20, 0x4E, 0x4F, 0x4E, 0x45, 0x00, (byte)0xBA, 0x4A, (byte)0xDF
        }, {
            (byte)0xD3, 0x00, 0x48, 0x40, (byte)0x90, 0x05, 0x14, 0x41, 0x44, 0x56, 0x4E, 0x55, 0x4C, 0x4C, 0x41,
            0x4E, 0x54, 0x45, 0x4E, 0x4E, 0x41, 0x20, 0x20, 0x4E, 0x4F, 0x4E, 0x45, 0x00, 0x00, 0x0D,
            0x54, 0x52, 0x49, 0x4D, 0x42, 0x4C, 0x45, 0x20, 0x41, 0x4C, 0x4C, 0x4F, 0x59, 0x14, 0x4E,
            0x61, 0x76, 0x20, 0x36, 0x2E, 0x31, 0x36, 0x20, 0x2F, 0x20, 0x42, 0x6F, 0x6F, 0x74, 0x20,
            0x35, 0x2E, 0x35, 0x36, 0x0A, 0x36, 0x30, 0x32, 0x34, 0x52, 0x34, 0x30, 0x30, 0x37, 0x39,
            0x3E, (byte)0x98, 0x67
        }
    };

    int parsedIndex = 0;
    byte[][] parsedResults = new byte[inputMessages.length][];
    byte[] combinedInput = new byte[Arrays.stream(inputMessages).map(a -> a.length).mapToInt(Integer::intValue).sum()];
    {
        int destStart = 0;
        for (byte[] bytes : inputMessages) {
            System.arraycopy(bytes, 0, combinedInput, destStart, bytes.length);
            destStart += bytes.length;
        }
    }
}