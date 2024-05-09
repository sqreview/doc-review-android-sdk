package com.signalquest.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MessageHandlerTest {
    boolean statusReceived = false;
    boolean llaReceived = false;
    @Test
    public void testHandler() {
        MessageHandler handler = new MessageHandler(new MessageHandler.MessageReceiver() {
            @Override
            public void receive(Status status) {
                statusReceived = true;
                assertNotNull(status);
                // spot testing a couple values
                assertEquals(93, status.getBattery());
                assertEquals(1712793328, status.getTime());
            }

            @Override
            public void receive(Location location) {
                llaReceived = true;
                assertNotNull(location);
                // spot testing a couple values
                assertEquals(-72.174282, location.getLongitude(), 0.0000001);
                assertEquals(43.412668, location.getLatitude(), 0.0000001);
            }
        });
        byte[] sqtpFrame = {
                -44, 0, -1, -1, -1, -1, -1, -1,    // Frame Header
                67, 18, 56, 0, -1, -1, -1, -1,     // Sub Frame Header  SQTP_ID_SITEPOINT_STATUS : 0x1243  Sub Frame Length: 56 Bytes
                -117, 12, 5, 0, -16, 38, 23, 102, 14, 4, -1, 0, 55, 12, 93, 1, -46, 4, 2, 15, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                85, 62, 0, 0,                      // Sub Frame CRC
                68, 18, 68, 0, -1, -1, -1, -1,     // Sub Frame Header  SQTP_ID_SITEPOINT_LLA : 0x1244  Sub Frame Length: 68 Bytes
                -117, 12, 5, 0, 0, 0, 0, 0, 4, -110, -80, 111, 39, 11, 82, -64, -120, 13, 22, 78, -46, -76, 69, 64, 0, 0, 0, 0, 0, 96, 91, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 36, 64,
                -49, -102, 0, 0,                   // Sub Frame CRC
                69, 18, 76, 0, -1, -1, -1, -1,     // Sub Frame Header  SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG : 0x1245  Sub Frame Length: 76 Bytes
                4, -110, -80, 111, 39, 11, 82, -64, -120, 13, 22, 78, -46, -76, 69, 64, 0, 0, 0, 0, 0, 96, 91, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 36, 64, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                100, -128, 0, 0,                   // Sub Frame CRC
                29, -91, 0, 0                      // Frame CRC
        };
        try {
            handler.parse(sqtpFrame);
        } catch (ApiException e) {
            fail(e.getMessage());
        }
        assertTrue(statusReceived);
        assertTrue(llaReceived);
        // NOTE: config not parsed in MessageHandler yet (only logs), so not tested
    }
}