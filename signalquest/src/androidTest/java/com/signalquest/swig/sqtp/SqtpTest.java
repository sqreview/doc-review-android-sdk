package com.signalquest.swig.sqtp;



import static com.signalquest.swig.sqsp.sqsp.SqspLbConfig_tByteArray;
import static com.signalquest.swig.sqsp.sqsp.SqspLbConfig_tSize;
import static com.signalquest.swig.sqsp.sqsp.SqspLla_tByteArray;
import static com.signalquest.swig.sqsp.sqsp.SqspLla_tSize;
import static com.signalquest.swig.sqsp.sqsp.SqspStatus_tByteArray;
import static com.signalquest.swig.sqsp.sqsp.SqspStatus_tSize;
import static com.signalquest.swig.sqsp.sqsp.sqspLbConfigCompact;
import static com.signalquest.swig.sqsp.sqsp.sqspLbConfigCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspLbConfigCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspLbConfigExpand;
import static com.signalquest.swig.sqsp.sqsp.sqspLbModeCCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspLbModeCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspLlaCompact;
import static com.signalquest.swig.sqsp.sqsp.sqspLlaCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspLlaCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspRspCInit;
import static com.signalquest.swig.sqsp.sqsp.sqspRspExpand;
import static com.signalquest.swig.sqsp.sqsp.sqspSolutionTypeLabel;
import static com.signalquest.swig.sqsp.sqsp.sqspSolutionTypeLabelCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusCCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusCopyCast;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusExpand;
import static com.signalquest.swig.sqsp.sqsp.sqspRspCCopy;

import static com.signalquest.swig.sqtp.SqtpStatus_t.SQTP_STATUS_SUCCESS;
import static com.signalquest.swig.sqtp.SqtpSubframeId_t.SQTP_ID_SITEPOINT_LLA;
import static com.signalquest.swig.sqtp.SqtpSubframeId_t.SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG;
import static com.signalquest.swig.sqtp.SqtpSubframeId_t.SQTP_ID_SITEPOINT_RELPOS;
import static com.signalquest.swig.sqtp.SqtpSubframeId_t.SQTP_ID_SITEPOINT_STATUS;

import static com.signalquest.swig.sqtp.sqtp.SqtpCheckValue_tSize;
import static com.signalquest.swig.sqtp.sqtp.sqtpFrameReaderInit;
import static com.signalquest.swig.sqtp.sqtp.sqtpFrameReaderNextStruct;
import static com.signalquest.swig.sqtp.sqtp.sqtpFrameWriterFinalize;
import static com.signalquest.swig.sqtp.sqtp.sqtpFrameWriterInit;
import static com.signalquest.swig.sqtp.sqtp.sqtpFrameWriterWriteDirect;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static java.nio.ByteBuffer.wrap;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.signalquest.swig.sqsp.SqspLbConfigC_t;
import com.signalquest.swig.sqsp.SqspLbConfig_t;
import com.signalquest.swig.sqsp.SqspLbModeC_t;
import com.signalquest.swig.sqsp.SqspLbMode_t;
import com.signalquest.swig.sqsp.SqspLlaC_t;
import com.signalquest.swig.sqsp.SqspLla_t;
import com.signalquest.swig.sqsp.SqspRspC_t;
import com.signalquest.swig.sqsp.SqspRsp_t;
import com.signalquest.swig.sqsp.SqspSolTypeLabel_t;
import com.signalquest.swig.sqsp.SqspStatusC_t;
import com.signalquest.swig.sqsp.SqspStatus_t;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4.class)
public class SqtpTest {
    @Test
    public void sqtp_sqspRspCCopy_test() {

        System.loadLibrary("sqsp_wrapper"); // We need this to generate test data and decode test data.
        System.loadLibrary("sqtp_wrapper");

        byte[] TestData = {55, 12, 99, 2, 107, 76, 0, 0, 0, 0, 0};

        SqspRsp_t myRsp = new  SqspRsp_t();
        SqspRspC_t myRspC = sqspRspCCopy(TestData);

        boolean result = sqspRspExpand( myRsp, myRspC);
        assertTrue(result);

        // getRsp()
        assertEquals( 3127, myRsp.getMfgDataId()          );
        assertEquals(   99, myRsp.getBatteryLevel()       );
        assertEquals(    2, myRsp.getSystemPowerState()   );
        assertEquals(19563, myRsp.getRunTimeRemaining()   );
        assertEquals(    0, myRsp.getSatelliteCount()     );
        assertEquals(    0, myRsp.getAidingBins()         );
        assertEquals(    0, myRsp.getDifferential()       );
        assertEquals(    0, myRsp.getGpsFixOk()           );
        assertEquals(    0, myRsp.getAmbiguity()          );
        assertEquals(    0, myRsp.getFix()                );
        assertEquals(    0, myRsp.getCoprocOk()           );
        assertEquals(    0, myRsp.getSurveyFailed()       );
        assertEquals(    0, myRsp.getSurveyPositionValid());
        assertEquals(    0, myRsp.getSurveyInProgress()   );
        assertEquals(    0, myRsp.getHeadingFlag()        );
        assertEquals(    0, myRsp.getPosValid()           );
        assertEquals(    0, myRsp.getValidTime()          );

        //  getRsp().getMode()
        assertEquals( 0, myRsp.getMode().getFixedBaseEnable() );
        assertEquals( 0, myRsp.getMode().getAutoSurveyEnable());
        assertEquals( 0, myRsp.getMode().getConnected()       );
        assertEquals( 0, myRsp.getMode().getCorrectionOutput());
        assertEquals( 0, myRsp.getMode().getCorrectionInput() );
        assertEquals( 0, myRsp.getMode().getTripodCorrections());
        assertEquals( 0, myRsp.getMode().getValidOrNotBusy()  );
        assertEquals( 0, myRsp.getMode().getBroadcastEnable() );
    }

    @Test
    public void sqtp_initial_test() {
        System.loadLibrary("sqsp_wrapper"); // We need this to generate test data and decode test data.
        System.loadLibrary("sqtp_wrapper");

        // TX Path
        // TXFrameBuffer is a Working Buffer sized for longest Frame we expect to create.
        ByteBuffer TXFrameBuffer = ByteBuffer.allocateDirect(1024);
        SqtpFrameWriter_t fw = new SqtpFrameWriter_t();
        byte[] myTXFrameMessage = null;

        // RX Path
        SqtpFrameReader_t   fr = new SqtpFrameReader_t();

        byte[] myStatusSubFrame = null;
        byte[] myLLASubFrame = null;
        byte[] myConfigSubFrame = null;

        short EXPECTED_MFG_DATA_ID = 0x0C37; //TODO: FIXME //#define EXPECTED_MFG_DATA_ID 0x0C37	//0x02E5  //  <Espressif ID, (2 bytes: 0x02E5)>	//	Should be 0x0C37 SignalQuest, LLC (aka 3127)
        boolean result;

        byte[] myMessageBuffer = null; // Declare where we will collect Finalized Message (and DEBUG check portions)

        byte[] DebugBuffer = new byte[100];  //DEBUG

        //*****************************************************************************************
        // Start of Test Data generation, using sqsp
        //*****************************************************************************************
        //-----------------------------------------------------------------------------------------
        // Create test vectors for testing sqtp layer using sqsp function calls...
        //  Generate the messages we expect to receive from the SitePoint (or similar such as NorthPoint)
        //-----------------------------------------------------------------------------------------

        //-----------------------------------------------------------------------------------------
        // Create myLbModeC, myLbMode
        //-----------------------------------------------------------------------------------------

        // NOTE: Get/Set of 'Compact' form of sqsp data structures not currently supported.
        SqspLbModeC_t myLbModeC = sqspLbModeCCreate( (char) 0, (char) 0, (char) 1, (char) 0, (char) 0, (char) 0, (char) 1, (char) 0); // Connected Rover
        // NOTE: bitfield comparison not supported: assertEquals(myLbModeC.getConnected(), 1);

        SqspLbMode_t myLbMode   = sqspLbModeCreate(  (char) 0, (char) 0, (char) 1, (char) 0, (char) 0, (char) 0, (char) 1, (char) 0); // Connected Rover
        assertEquals(1, myLbMode.getValidOrNotBusy());

        //-----------------------------------------------------------------------------------------
        // Create myRspC
        //-----------------------------------------------------------------------------------------

        //TODO: Test sqspRspInit() functionality here?

        // NOTE: Get/Set of 'Compact' form of sqsp data structures not currently supported.
        SqspRspC_t myRspC = new  SqspRspC_t();
        SqspRsp_t myRsp = new  SqspRsp_t();

        result = sqspRspCInit(
                myRspC,
                EXPECTED_MFG_DATA_ID,
                (char) 93, (char) 1, (short) 1234, (char) 2, (char) 0x0F, (char) 0, (char) 0,
                (char) 0, (char) 0, (char) 0, (char) 0, (char) 1, (char) 0, (char) 0, (char) 1,
                (char) 1, myLbModeC);
        assertTrue(result);

        result = sqspRspExpand( myRsp, myRspC);
        assertTrue(result);
        assertEquals(1234, myRsp.getRunTimeRemaining());

        //-----------------------------------------------------------------------------------------
        // Create myStatusC, myStatus
        //-----------------------------------------------------------------------------------------

        // NOTE: Get/Set of 'Compact' form of sqsp data structures not currently supported.
        SqspStatusC_t myStatusC = sqspStatusCCreate(
                330891, (short) 2024, (char) 4, (char) 10, (char) 19, (char) 55, (char) 28,
                (char) 5, (char) 4, (char) -1,
                myRspC);
        SqspStatus_t myStatus = new SqspStatus_t();

        result = sqspStatusExpand( myStatus, myStatusC);
        assertTrue(result);

        // Test sqspSolutionTypeLabel() and sqspSolutionTypeLabelCopy() SWIG wrappers.
        String x = sqspSolutionTypeLabel(myStatus.getSolType());
        SqspSolTypeLabel_t xx = sqspSolutionTypeLabelCopy(myStatus.getSolType());
        assertEquals(x, xx.getString());

        assertEquals(      330891, myStatus.getITOW());
        assertEquals(  1712793328, myStatus.getTime());
        assertEquals(           5, myStatus.getSolType());
        assertEquals(           4, myStatus.getNumSV());
        assertEquals(         255, myStatus.getBattery());
        assertEquals(           0, myStatus.getReserved());

        // getRsp()
        assertEquals( 3127, myStatus.getRsp().getMfgDataId()          );
        assertEquals(   93, myStatus.getRsp().getBatteryLevel()       );
        assertEquals(    1, myStatus.getRsp().getSystemPowerState()   );
        assertEquals( 1234, myStatus.getRsp().getRunTimeRemaining()   );
        assertEquals(    2, myStatus.getRsp().getSatelliteCount()     );
        assertEquals(   15, myStatus.getRsp().getAidingBins()         );
        assertEquals(    0, myStatus.getRsp().getDifferential()       );
        assertEquals(    0, myStatus.getRsp().getGpsFixOk()           );
        assertEquals(    0, myStatus.getRsp().getAmbiguity()          );
        assertEquals(    0, myStatus.getRsp().getFix()                );
        assertEquals(    0, myStatus.getRsp().getCoprocOk()           );
        assertEquals(    0, myStatus.getRsp().getSurveyFailed()       );
        assertEquals(    1, myStatus.getRsp().getSurveyPositionValid());
        assertEquals(    0, myStatus.getRsp().getSurveyInProgress()   );
        assertEquals(    0, myStatus.getRsp().getHeadingFlag()        );
        assertEquals(    1, myStatus.getRsp().getPosValid()           );
        assertEquals(    1, myStatus.getRsp().getValidTime()          );

        //  getRsp().getMode()
        assertEquals( 0, myStatus.getRsp().getMode().getFixedBaseEnable() );
        assertEquals( 0, myStatus.getRsp().getMode().getAutoSurveyEnable());
        assertEquals( 1, myStatus.getRsp().getMode().getConnected()       );
        assertEquals( 0, myStatus.getRsp().getMode().getCorrectionOutput());
        assertEquals( 0, myStatus.getRsp().getMode().getCorrectionInput() );
        assertEquals( 0, myStatus.getRsp().getMode().getTripodCorrections());
        assertEquals( 1, myStatus.getRsp().getMode().getValidOrNotBusy()  );
        assertEquals( 0, myStatus.getRsp().getMode().getBroadcastEnable() );

        assertEquals(0, myStatus.getReserved2());

        //-----------------------------------------------------------------------------------------
        // Create myLlaC, myLla
        //-----------------------------------------------------------------------------------------

        //------------------------------------------------------------------------------------------
        //Reference :
        //                                                      // 9 Lash Road	//	ECEF
        //int64_t	SitePoint_LAT = ( 43.412668	* 1000000000);	//  43.41266	//	 1404811.2657
        //int64_t	SitePoint_LON = (-72.174282	* 1000000000);	// -72.17428	//	-4400589.9369
        //int64_t	SitePoint_HGT = ( 109.500	* 10000     );	//  109.50		//	 4383408.2035
        //int64_t	SitePoint_ACC = ( 5         * 1000      );	//	5			//	 5
        //------------------------------------------------------------------------------------------

        SqspLla_t myLla = sqspLlaCreate(330891, -72.174282, 43.412668, 109.500, 5.0, 5.0, 10.0);
        // NOTE: Get/Set of 'Compact' form of sqsp data structures not currently supported.
        SqspLlaC_t myLlaC = new SqspLlaC_t();

        result = sqspLlaCompact(myLlaC, myLla);
        assertTrue(result);

        assertEquals(330891        , myLla.getITOW() );
        assertEquals(     0        , myLla.getReserved()  );

        assertEquals(   -72.174282 , myLla.getLon()   , 0.0000001);
        assertEquals(    43.412668 , myLla.getLat()   , 0.0000001);
        assertEquals(   109.500    , myLla.getHeight(), 0.0001);
        assertEquals(     5.0      , myLla.getHMSL()  , 0.0001);
        assertEquals(     5.0      , myLla.getHAcc()   , 0.01);
        assertEquals(    10.0      , myLla.getVAcc()   , 0.01);

        //TODO: Add tests for the 'compact' myLlaC, and/or perform a conversion back to the
        // 'expanded form and compare against myLla. SqspLlaC_t currently needs fixups for SWIGJAVA.
//        assertEquals(  330891        , myLlaC.getITOW() );

        //-----------------------------------------------------------------------------------------
        // Create myLbConfigC, myLbConfig
        //-----------------------------------------------------------------------------------------

        SqspLbConfig_t myLbConfig = sqspLbConfigCreate(-72.174282, 43.412668, 109.500, 5.0, 5.0, 10.0, 0, myLbMode);

        assertEquals(-72.174282, myLbConfig.getLon(), 0.000001);
        assertEquals( 43.412668, myLbConfig.getLat(), 0.000001);
        assertEquals(109.500,    myLbConfig.getHeight(), 0.001);
        assertEquals(  5.0,      myLbConfig.getHMSL()  , 0.0001);
        assertEquals(  5.0,      myLbConfig.getHAcc()   , 0.01);
        assertEquals( 10.0,      myLbConfig.getVAcc()   , 0.01);

        assertEquals(0, myLbConfig.getMode().getFixedBaseEnable()    );
        assertEquals(0, myLbConfig.getMode().getAutoSurveyEnable()   );
        assertEquals(1, myLbConfig.getMode().getConnected()          );
        assertEquals(0, myLbConfig.getMode().getCorrectionOutput()   );
        assertEquals(0, myLbConfig.getMode().getCorrectionInput()    );
        assertEquals(0, myLbConfig.getMode().getTripodCorrections()  );
        assertEquals(1, myLbConfig.getMode().getValidOrNotBusy()     );
        assertEquals(0, myLbConfig.getMode().getBroadcastEnable()    );

        //NOTE: getReserved()returns a String of 4 chars due to declaration:
        // "uint8_t      reserved[4]"
        //         mapped to:
        // "char       reserved[4]
        assertEquals("", myLbConfig.getReserved()  );

        // Take a 'round trip' through some LLA conversions functions
        // sqspLbConfigCompact(myLbConfigC,myLbConfig) ==> sqspLbModeCompact() == > myLbConfigC,
        // sqspLbConfigExpand(myLbConfigC, myLbConfigC) ==> sqspLbModeExpand() ==> ExpandedMyLbConfigC
        // Check results,

        // NOTE: Get/Set of 'Compact' form of sqsp data structures not currently supported.
        SqspLbConfigC_t myLbConfigC = new SqspLbConfigC_t();
        result = sqspLbConfigCompact(myLbConfigC,myLbConfig);
        assertTrue(result);

        SqspLbConfig_t ExpandedMyLbConfigC = new SqspLbConfig_t();
        result = sqspLbConfigExpand(ExpandedMyLbConfigC, myLbConfigC);
        assertTrue(result);

        assertEquals(-72.174282 , ExpandedMyLbConfigC.getLon()   , 0.0000001);
        assertEquals( 43.412668 , ExpandedMyLbConfigC.getLat()   , 0.0000001);
        assertEquals(109.500    , ExpandedMyLbConfigC.getHeight(), 0.0001);
        assertEquals(  5.0      , ExpandedMyLbConfigC.getHMSL()  , 0.0001);
        assertEquals(  5.0,      ExpandedMyLbConfigC.getHAcc()    , 0.01);
        assertEquals( 10.0,      ExpandedMyLbConfigC.getVAcc()    , 0.01);
        assertEquals(  0  ,      ExpandedMyLbConfigC.getDuration()            );

        assertEquals(0, ExpandedMyLbConfigC.getMode().getFixedBaseEnable()  );
        assertEquals(0, ExpandedMyLbConfigC.getMode().getAutoSurveyEnable() );
        assertEquals(1, ExpandedMyLbConfigC.getMode().getConnected()        );
        assertEquals(0, ExpandedMyLbConfigC.getMode().getCorrectionOutput() );
        assertEquals(0, ExpandedMyLbConfigC.getMode().getCorrectionInput()  );
        assertEquals(0, ExpandedMyLbConfigC.getMode().getTripodCorrections());
        assertEquals(1, ExpandedMyLbConfigC.getMode().getValidOrNotBusy()   );
        assertEquals(0, ExpandedMyLbConfigC.getMode().getBroadcastEnable()  );

        // Compare 'round trip' results
        assertEquals(myLbConfig.getLon()      , ExpandedMyLbConfigC.getLon()     , 0.0000001);
        assertEquals(myLbConfig.getLat()      , ExpandedMyLbConfigC.getLat()     , 0.0000001);
        assertEquals(myLbConfig.getHeight()   , ExpandedMyLbConfigC.getHeight()  , 0.0001);
        assertEquals(myLbConfig.getHMSL()     , ExpandedMyLbConfigC.getHMSL()    , 0.0001);
        assertEquals(myLbConfig.getHAcc()     , ExpandedMyLbConfigC.getHAcc()    , 0.01);
        assertEquals(myLbConfig.getVAcc()     , ExpandedMyLbConfigC.getVAcc()    , 0.01);
        assertEquals(myLbConfig.getDuration() , ExpandedMyLbConfigC.getDuration()            );

        //  Test return of Mode from myLbConfig
        SqspLbMode_t myTestMode = myLbConfig.getMode();

        assertEquals(0, myTestMode.getFixedBaseEnable()  );
        assertEquals(0, myTestMode.getAutoSurveyEnable() );
        assertEquals(1, myTestMode.getConnected()        );
        assertEquals(0, myTestMode.getCorrectionOutput() );
        assertEquals(0, myTestMode.getCorrectionInput()  );
        assertEquals(0, myTestMode.getTripodCorrections());
        assertEquals(1, myTestMode.getValidOrNotBusy()   );
        assertEquals(0, myTestMode.getBroadcastEnable()  );

//TODO: Expand/Compact to check Math?  [Pick better numbers to stress math?]
//        bool sqspLbConfigExpand( SqspLbConfig_t *lbc, const SqspLbConfigC_t *lbcs )
//        bool sqspLbConfigCompact( SqspLbConfigC_t *lbc, const SqspLbConfig_t *lbcs )

        //*****************************************************************************************
        // End of Test Data generation, Start of sqtp TX path testing
        //*****************************************************************************************

        //-----------------------------------------------------------------------------------------
        // Create and initialize Frame Writer
        // NOTE: Frame Writer (implicitly starts 1st Frame by inserting empty 8 byte Frame header))
        //-----------------------------------------------------------------------------------------

        //NOTE: Do not use TXFrameBuffer.array().length, wrong value, it's (1031-1024)=7 bytes longer
        SqtpStatus_t Sresult = sqtpFrameWriterInit( fw, TXFrameBuffer.array(), TXFrameBuffer.array().length);

        //-----------------------------------------------------------------------------------------
        // Test the Byte[] converters
        //-----------------------------------------------------------------------------------------
        int myResult = 0;

//        // Convert myRspC byte array to a SqspStatusC_t and then to a SqspStatus_t and compare.
//        SqspStatusC_t myRspCfromByteArray = new SqspStatusC_t();
//        SqspStatusC_tByteArray(0, SqspStatusC_tSize(), myRspCfromByteArray);

        // Convert myStatus to a byte[] array   //TODO: Consider a Java wrapper for SqspStatus_tByteArray() that returns a byte[] array?
        myStatusSubFrame = new byte[SqspStatus_tSize()]; // resize mySubFrame
        myResult = SqspStatus_tByteArray(myStatusSubFrame, myStatusSubFrame.length, myStatus);
//        myResult = SqspStatus_tByteArray(myStatusSubFrame, myStatus);
        assertEquals(0, myResult);
        {
            byte[] StatusVerificationData = {-117, 12, 5, 0, -16, 38, 23, 102, 5, 4, -1, 0, 55, 12, 93, 1, -46, 4, 2, 15, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
            assertArrayEquals(StatusVerificationData, myStatusSubFrame);
        }

        // Convert myLla to a byte[] array   //TODO: Consider a Java wrapper for SqspStatus_tByteArray() that returns a byte[] array?
        myLLASubFrame = new byte[SqspLla_tSize()]; // resize myLLASubFrame
        myResult = SqspLla_tByteArray(myLLASubFrame, myLLASubFrame.length, myLla);
//        myResult = SqspLla_tByteArray(myLLASubFrame, myLla);
        assertEquals(0, myResult);
        {
            byte[] LLAVerificationData = {-117, 12, 5, 0, 0, 0, 0, 0, 4, -110, -80, 111, 39, 11, 82, -64, -120, 13, 22, 78, -46, -76, 69, 64, 0, 0, 0, 0, 0, 96, 91, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 36, 64};
            assertArrayEquals(LLAVerificationData, myLLASubFrame);
        }

        // Convert myLla to a byte[] array   //TODO: Consider a Java wrapper for SqspStatus_tByteArray() that returns a byte[] array?
        myConfigSubFrame = new byte[SqspLbConfig_tSize()]; // resize myConfigSubFrame
        myResult = SqspLbConfig_tByteArray(myConfigSubFrame, myConfigSubFrame.length, myLbConfig);
//        myResult = SqspLbConfig_tByteArray(myConfigSubFrame, myLbConfig);
        assertEquals(0, myResult);
        {
            byte[] ConfigVerificationData = {4, -110, -80, 111, 39, 11, 82, -64, -120, 13, 22, 78, -46, -76, 69, 64, 0, 0, 0, 0, 0, 96, 91, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 36, 64, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0};
            assertArrayEquals(ConfigVerificationData, myConfigSubFrame);
        }

        //-----------------------------------------------------------------------------------------
        // Create Some Sub Frames in the Frame Buffer and check contents as we progress
        //-----------------------------------------------------------------------------------------

//DEBUG:        DebugBuffer = Arrays.copyOf(TXFrameBuffer.array(), (int) fw.getIndex()); // NOTE: Arrays.copyOf() returns a byte[] array. // DEBUG: retrieve portion of Message for DEBUG check

        // NOTE: TXFrameBuffer.array() should be all '0's here, and length of a Frame Header: 8 bytes

        //-----------------------------------------------------------------------------------------
        // Add First Sub Frame to Frame in Frame Buffer
        //-----------------------------------------------------------------------------------------

        Sresult = sqtpFrameWriterWriteDirect(fw, SQTP_ID_SITEPOINT_STATUS, myStatusSubFrame, myStatusSubFrame.length);
//DEBUG:        myMessageBuffer = Arrays.copyOf(TXFrameBuffer.array(), (int) fw.getIndex());  //DEBUG: retrieve portion of Message for DEBUG check

        Sresult = sqtpFrameWriterWriteDirect(fw, SQTP_ID_SITEPOINT_LLA, myLLASubFrame, myLLASubFrame.length);
//DEBUG:        myMessageBuffer = Arrays.copyOf(TXFrameBuffer.array(), (int) fw.getIndex());  //DEBUG: retrieve portion of Message for DEBUG check

        Sresult = sqtpFrameWriterWriteDirect(fw, SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG, myConfigSubFrame, myConfigSubFrame.length);
//DEBUG:        myMessageBuffer = Arrays.copyOf(TXFrameBuffer.array(), (int) fw.getIndex());  //DEBUG: retrieve portion of Message for DEBUG check

        SqtpStatus_t status = sqtpFrameWriterFinalize(fw);
        assertEquals(SQTP_STATUS_SUCCESS, status);

        //-----------------------------------------------------------------------------------------
        // Retrieve Frame Buffer as a byte[] array
        //-----------------------------------------------------------------------------------------

        // First Method: Uses System.arraycopy(). Fast as possible, but requires a properly sized
        // byte[] array for each invocation.
        myTXFrameMessage = new byte[(int) fw.getIndex()];
        System.arraycopy(TXFrameBuffer.array(), 0, myTXFrameMessage, 0, (int) fw.getIndex()); //TXFrameBuffer.array().length,

        // Second Method: Uses Arrays.copyOf().  Not so fast, but returns a properly sized
        // byte[] array without having to resize it everytime.
        myTXFrameMessage = null;
        myTXFrameMessage = Arrays.copyOf(TXFrameBuffer.array(), (int) fw.getIndex());

        //-----------------------------------------------------------------------------------------
        //  Leave for now:
        //  DEBUG HELPERS: The following lines are useful when debugging, to obtain the full message
        //  for generating Verification data and checking contents.
        //-----------------------------------------------------------------------------------------
        byte[] extractedByteArray = Arrays.copyOfRange(myTXFrameMessage, 100, 212);  //DEBUG : Only get first 100 bytes out of debugger, get the rest for analysis
               extractedByteArray = Arrays.copyOfRange(myTXFrameMessage, 200, 212);  //DEBUG : Only get first 100 bytes out of debugger, get the rest for analysis
        //-----------------------------------------------------------------------------------------

        {   //  1 Entire Frame containing 3 Sub Frames : SQTP_ID_SITEPOINT_STATUS, SQTP_ID_SITEPOINT_LLA, SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG
            byte[] VerificationData =
            {
                -44, 0, -1, -1, -1, -1, -1, -1,    // Frame Header
                67, 18, 56, 0, -1, -1, -1, -1,     // Sub Frame Header  SQTP_ID_SITEPOINT_STATUS : 0x1243  Sub Frame Length: 56 Bytes
                -117, 12, 5, 0, -16, 38, 23, 102,  5, 4, -1, 0, 55, 12, 93, 1, -46, 4, 2, 15, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                -72, -120, 0, 0,                   // Sub Frame CRC
                68, 18, 68, 0, -1, -1, -1, -1,     // Sub Frame Header  SQTP_ID_SITEPOINT_LLA : 0x1244  Sub Frame Length: 68 Bytes
                -117, 12, 5, 0, 0, 0, 0, 0, 4, -110, -80, 111, 39, 11, 82, -64, -120, 13, 22, 78, -46, -76, 69, 64, 0, 0, 0, 0, 0, 96, 91, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 36, 64,
                -49, -102, 0, 0,                   // Sub Frame CRC
                69, 18, 76, 0, -1, -1, -1, -1,     // Sub Frame Header  SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG : 0x1245  Sub Frame Length: 76 Bytes
                4, -110, -80, 111, 39, 11, 82, -64, -120, 13, 22, 78, -46, -76, 69, 64, 0, 0, 0, 0, 0, 96, 91, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 20, 64, 0, 0, 0, 0, 0, 0, 36, 64, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                100, -128, 0, 0,                   // Sub Frame CRC
                29, -91, 0, 0                      // Frame CRC
            };
            assertTrue(Arrays.equals(VerificationData, myTXFrameMessage));
        }

        //*****************************************************************************************
        // End of sqtp TX path testing, Start of sqtp RX path testing
        //*****************************************************************************************

        //-----------------------------------------------------------------------------------------
        // Create amd initialize Frame Reader
        // NOTE: Frame Writer (implicitly starts 1st Frame by inserting empty 8 byte Frame header))
        //-----------------------------------------------------------------------------------------

        //-----------------------------------------------------------------------------------------
        // Let's copy myTXFrameMessage into myRXFrameMessage to simulate reception of a Frame:
        //-----------------------------------------------------------------------------------------

        //  Use .allocateDirect() (required) for data buffer of data passed into
        //  sqtpFrameReaderInit().
        //  BUT there's an issue: The .array() returned is offset by .arrayOffset() bytes(!AARGH!).
        //  THe value returned by.position() has a surprising value also.
        //  This due to the addition of 7 bytes to the data array when .allocateDirect() allocates
        //  memory:
        //  From:
        //  @see https://android.googlesource.com/platform/libcore/+/android-6.0.1_r21/luni/src/main/java/java/nio/ByteBuffer.java#68
        //      // Ensure alignment by 8.
        //      MemoryBlock memoryBlock = MemoryBlock.allocate(capacity + 7);
        //
        //  If we're lucky, (.arrayOffset == 0), which it occasionally is, then all is good, but
        //  usually it is NOT!
        //
        //  You can see this in the difference between .array().length and the declared capacity
        //  (aka .capacity()) when the ByteBuffer was created of 7 bytes, 4 of which are at the
        //  beginning of the .array().
        //  The incorrect value for .array.length appears to be deliberate to guarantee that it is
        //  possible to get an 8 byte alignment in the allocated buffer.
        //
        //  The cure to make this work is to allocate a ByteBuffer.allocateDirect() and then
        //  'wrap()' it.  'wrap()' has the virtue of returning a Byte Buffer that has an
        //  .arrayOffset() of '0' .

        //------------------------
        // Get a data buffer using ByteBuffer.allocateDirect() that Java GC won't move on us
        // (which breaks assumptions/expectations in the underlying 'C' sqsp/sqtp libraries.
        ByteBuffer WorkingBuffer = ByteBuffer.allocateDirect(1024);

        // Establish a ByteBuffer with a 'view'/of WorkingBuffer.array() that has an .arrayOffset
        // of '0' .
        // NOTE: We'd like to use ByteBuffer slice(int index, int length), but it's not available unless \
        // we step up to Android 13+ .
        ByteBuffer myRXFrameMessage = wrap(WorkingBuffer.array(), 0, myTXFrameMessage.length);

        // Load our data into myRXFrameMessage.array() with an .arrayOffset of '0' so that our data
        // is properly aligned at the beginning of the byte[] returned by myRXFrameMessage.array().
        //
        // NOTE: .put() both loads the 'myTXFrameMessage[]' byte array into the myRXFrameMessage
        // ByteBuffer and also sets the new value for .limit() to the size of the data[] byte array.
        // Use myRXFrameMessage.limit() for setting the length passed to the sqtpFrameReaderInit()
        // function (and elsewhere), NOT myRXFrameMessage.array().length, which is the WRONG value!
        myRXFrameMessage.put(myTXFrameMessage);
        //------------------------

        //  Let's initialize a Frame Reader and pass the 'received' myRXFrameMessage into it.
        Sresult = sqtpFrameReaderInit(fr, myRXFrameMessage.array(), myRXFrameMessage.limit());
        assertEquals(SQTP_STATUS_SUCCESS, Sresult);

        // NOTE: Determine size of mySubFrameByteArray[] based on fr.getSubframe().getId() or
        // fr.getSubframe().getLength() or simply use something bigger than you expect to see.
        byte[] mySubFrameByteArray = new byte[(int)fr.getSubframe().getLength()];

        // Check that we can retrieve a Sub Frame struct:
        SqtpSubframe_t xSubFrame = new SqtpSubframe_t();
        xSubFrame = fr.getSubframe();
        assertEquals(44, xSubFrame.getLength());

        mySubFrameByteArray = getFrameReaderSubFrameBytes(fr, myRXFrameMessage);

        // Check mySubFrameByteArray[] byte array against VerificationData[]
        {
            byte[] VerificationData = {-117, 12, 5, 0, -16, 38, 23, 102,  5, 4, -1, 0, 55, 12, 93, 1, -46, 4, 2, 15, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
            assertArrayEquals(VerificationData, mySubFrameByteArray);
        }

        // Now copy and cast mySubFrameByteArray[] into a SqspStatus_t struct and check it.
        {
            SqspStatus_t test = sqspStatusCopyCast(mySubFrameByteArray);

            assertEquals(    330891, test.getITOW());
            assertEquals(1712793328, test.getTime());
            assertEquals(         5, test.getSolType());
            assertEquals(         4, test.getNumSV());
            assertEquals(       255, test.getBattery());
            assertEquals(         0, test.getReserved());

            // getRsp()
            assertEquals(3127, test.getRsp().getMfgDataId());
            assertEquals(93, test.getRsp().getBatteryLevel());
            assertEquals(1, test.getRsp().getSystemPowerState());
            assertEquals(1234, test.getRsp().getRunTimeRemaining());
            assertEquals(2, test.getRsp().getSatelliteCount());
            assertEquals(15, test.getRsp().getAidingBins());
            assertEquals(0, test.getRsp().getDifferential());
            assertEquals(0, test.getRsp().getGpsFixOk());
            assertEquals(0, test.getRsp().getAmbiguity());
            assertEquals(0, test.getRsp().getFix());
            assertEquals(0, test.getRsp().getCoprocOk());
            assertEquals(0, test.getRsp().getSurveyFailed());
            assertEquals(1, test.getRsp().getSurveyPositionValid());
            assertEquals(0, test.getRsp().getSurveyInProgress());
            assertEquals(0, test.getRsp().getHeadingFlag());
            assertEquals(1, test.getRsp().getPosValid());
            assertEquals(1, test.getRsp().getValidTime());

            //  getRsp().getMode()
            assertEquals(0, test.getRsp().getMode().getFixedBaseEnable());
            assertEquals(0, test.getRsp().getMode().getAutoSurveyEnable());
            assertEquals(1, test.getRsp().getMode().getConnected());
            assertEquals(0, test.getRsp().getMode().getCorrectionOutput());
            assertEquals(0, test.getRsp().getMode().getCorrectionInput());
            assertEquals(0, test.getRsp().getMode().getTripodCorrections());
            assertEquals(1, test.getRsp().getMode().getValidOrNotBusy());
            assertEquals(0, test.getRsp().getMode().getBroadcastEnable());

            assertEquals(test.getReserved2(), 0);
        }

//Reference:
//        typedef struct SqspStatus_s
//        {
//            int         iTOW;                    //  GPS time of week [ms]
//            int         time;                    //  UTC time; can't use time_t
//            char        solType;                 //  GNSS Solution Type (SqspSolType_t)
//            char        numSV;                   //  Number of satellites used in
//            //    position solution
//            char        battery;                 //  Current charge of battery, if any
//            //    (or 0 if none)
//            char        reserved;                //  Unused
//            SqspRsp_t   rsp;
//            char        reserved2;               //  Pad length to a multiple of 4 bytes
//        } SqspStatus_t;
//
//        typedef struct SqspRsp_s
//        {
//            short    mfgDataId;                   //  ???
//            char     batteryLevel;                //  Battery level [0-100]
//            char     systemPowerState;            //  (Dis)charge state
//            short    runTimeRemaining;            //  Run/charge time estimate in minutes
//            char     satelliteCount;              //  Current Number of Satellites in use
//            char     aidingBins;                  //  Each bit denotes an RTCM correction
//            //    was received on an 8-second cycle.
//            char     differential;                //  1: GNSS aided with Differential
//            //    Corrections
//            char     gpsFixOk;                    //  1: Valid Fix
//            char     ambiguity;                   //  RTK Ambiguity:
//            //    0: No RTK
//            //    1: Float RTK operation
//            //    2: Fixed RTK operation
//            char     fix;                         //  GNSS Fix Type:
//            //    0: No Fix
//            //    1: No Fix
//            //    2: 2D Fix
//            //    3: 3D Fix
//            //    4: GNSS
//            //    5: Fixed Base (Time Only) Fix
//            char     coprocOk;                    //  Factory Use
//            char     surveyFailed;                //  1: Survey In Operation Failed
//            char     surveyPositionValid;         //  1: Survey In Operation Completed
//            char     surveyInProgress;            //  1: Survey In Busy
//            char     headingFlag;                 //  Factory Use
//            char     posValid;                    //  1: Relative NED position is valid
//            char     validTime;                   //  1: UTC Time is Valid (GNSS-derived)
//            SqspLbMode_t mode;
//        } SqspRsp_t;
//
//        typedef struct SqspLbMode_s
//        {
//            char    fixedBaseEnable;              //  0: Operate as rover
//                                                  //  1: Operate as base
//            char    autoSurveyEnable;             //  0: Operate as base using LLA
//                                                  //    [Is this correct?]
//                                                  //  1: Operate as base attempting self
//                                                  //    survey
//            char    connected;                    //  READ ONLY - Ignored on write.
//            char    correctionOutput;             //  0: Prefer BLE Corrections
//                                                  //  1: Prefer Wifi Corrections
//            char    correctionInput;              //  0: Prefer NTRIP aiding over BLE
//                                                  //  1: Prefer Local FIXED BASE RTCM over
//                                                  //    BLE
//            char    tripodCorrections;            //  1: Connected to a base and receiving
//                                                  //    corrections
//            char    validOrNotBusy;               //  1: "Data is Valid" or "We're BUSY".
//            char    broadcastEnable;              //  Used when fixedBaseEnable==1
//        } SqspLbMode_t;

    }

    @Test
    public void useSqtp() {
        // Context of the app under test.
        System.loadLibrary("sqsp_wrapper"); // We need this to generate test data and decode test data.
        System.loadLibrary("sqtp_wrapper");

//        byte[] VerificationData = {124, 0, -1, -1, -1, -1, -1, -1, 67, 18, 56, 0, -1, -1, -1, -1, -117, 12, 5, 0, -16, 38, 23, 102, 14, 4, -1, 0, 55, 12, 93, 1, -46, 4, 2, 15, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 85, 62, 0, 0, 67, 18, 56, 0, -1, -1, -1, -1, -117, 12, 5, 0, -16, 38, 23, 102, 14, 4, -1, 0, 55, 12, 93, 1, -46, 4, 2, 15, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 85, 62, 0, 0, -87, -38, 0, 0};
//        {   //  1 Entire Frame containing 3 Sub Frames : SQTP_ID_SITEPOINT_STATUS, SQTP_ID_SITEPOINT_LLA, SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG
            byte[] VerificationData =
            {
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
//            assertTrue(Arrays.equals(VerificationData, myTXFrameMessage));
//        }

        parse(VerificationData);
    }

    //*******************************************************************************************
//    public class MessageHandler {
//        public interface MessageReceiver {
//            void receive(Status status);
//            void receive(Location location);
//        }

        private static boolean initialized = false;
//        public MessageHandler(com.signalquest.api.MessageHandler.MessageReceiver delegate) {
//            initialize();
//            this.delegate = delegate;
//        }

//        private synchronized void initialize() {
//            if (!initialized) {
//                System.loadLibrary("sqsp_wrapper");
//                System.loadLibrary("sqtp_wrapper");
//                initialized = true;
//            }
//        }

        private final static String LOG_TAG = "SQTP";
//        private final com.signalquest.api.MessageHandler.MessageReceiver delegate;

//        public void parse(byte[] data) throws SdkException {
        public void parse(byte[] data) {
//            if (data == null || data.length == 0) {
//                throw new SdkException("No data to parse");
//            }

//            if (delegate == null) {
//                Log.w(LOG_TAG, "No receiver");
//                return;
//            }

            //  Use .allocateDirect() (required) to obtain a data buffer Java GC to pass into
            //  sqtpFrameReaderInit().
            //  BUT there's an issue: The .array() returned is offset by .arrayOffset() bytes
            //  (!AARGH!).
            //  The value returned by.position() has a surprising value also.
            //  This due to the addition of 7 bytes to the data array when .allocateDirect()
            //  allocates memory:
            //  From:
            //  @see https://android.googlesource.com/platform/libcore/+/android-6.0.1_r21/luni/src/main/java/java/nio/ByteBuffer.java#68
            //      // Ensure alignment by 8.
            //      MemoryBlock memoryBlock = MemoryBlock.allocate(capacity + 7);
            //
            //  If we're lucky, (.arrayOffset == 0), which it occasionally is, then all is good, but
            //  usually it is NOT!
            //
            //  You can see this in the difference between .array().length and the declared capacity
            //  (aka .capacity()) when the ByteBuffer was created of 7 bytes, 4 of which are at the
            //  beginning of the .array().
            //  The incorrect value for .array.length appears to be deliberate to guarantee that it is
            //  possible to get an 8 byte alignment in the allocated buffer.
            //
            //  The cure to make this work is to allocate a ByteBuffer.allocateDirect() and then
            //  'wrap()' it.  'wrap()' has the virtue of returning a Byte Buffer that has an
            //  .arrayOffset() of '0' .

            //------------------------
            // Set capacity of WorkingBuffer to largest MTU value (or larger), which will be roughly
            // 30-50 bytes more than the largest message you should be able to receive, (due to
            // BLE framing overhead).

            // Get a data buffer using ByteBuffer.allocateDirect() that Java GC won't move on us
            // (which breaks assumptions/expectations in the underlying 'C' sqsp/sqtp libraries.
            ByteBuffer WorkingBuffer = ByteBuffer.allocateDirect(1024);

            // Establish a ByteBuffer with a 'view' of WorkingBuffer.array() that has an
            // .arrayOffset of '0' .
            //
            // NOTE: We'd like to use ByteBuffer slice(int index, int length), but it's not
            // available unless we step up to Android 13+ .
            ByteBuffer myRXFrameMessage = wrap(WorkingBuffer.array(), 0, data.length);

            // NOTE: .put() both loads the 'data[]' byte array into the myRXFrameMessage ByteBuffer
            // and also sets the new value for .limit() to the size of the data[] byte array.
            // Use myRXFrameMessage.limit() for setting the length passed to the
            // sqtpFrameReaderInit() function (and elsewhere), NOT myRXFrameMessage.array().length,
            // which is WRONG.
            myRXFrameMessage.put(data);
            //------------------------

            //  Let's initialize a Frame Reader and pass the 'received' myRXFrameMessage into it.
            SqtpFrameReader_t reader = new SqtpFrameReader_t();
            SqtpStatus_t initialized = sqtpFrameReaderInit(reader, myRXFrameMessage.array(), myRXFrameMessage.limit());
            assertEquals(SQTP_STATUS_SUCCESS, initialized);

            //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

            while (reader.getStatus() == SQTP_STATUS_SUCCESS) {
                SqtpSubframeId_t messageId = reader.getSubframe().getId();
                if (messageId == SQTP_ID_SITEPOINT_STATUS) {
                    byte[] bytes = getFrameReaderSubFrameBytes(reader, myRXFrameMessage);
                    SqspStatus_t sitePointStatus = sqspStatusCopy(bytes);

                    assertEquals(    330891, sitePointStatus.getITOW());
                    assertEquals(1712793328, sitePointStatus.getTime());
                    assertEquals(         5, sitePointStatus.getSolType());
                    assertEquals(         4, sitePointStatus.getNumSV());
                    assertEquals(       255, sitePointStatus.getBattery());
                    assertEquals(         0, sitePointStatus.getReserved());

                    // getRsp()
                    assertEquals( 3127, sitePointStatus.getRsp().getMfgDataId()          );
                    assertEquals(   93, sitePointStatus.getRsp().getBatteryLevel()       );
                    assertEquals(    1, sitePointStatus.getRsp().getSystemPowerState()   );
                    assertEquals( 1234, sitePointStatus.getRsp().getRunTimeRemaining()   );
                    assertEquals(    2, sitePointStatus.getRsp().getSatelliteCount()     );
                    assertEquals(   15, sitePointStatus.getRsp().getAidingBins()         );
                    assertEquals(    0, sitePointStatus.getRsp().getDifferential()       );
                    assertEquals(    0, sitePointStatus.getRsp().getGpsFixOk()           );
                    assertEquals(    0, sitePointStatus.getRsp().getAmbiguity()          );
                    assertEquals(    0, sitePointStatus.getRsp().getFix()                );
                    assertEquals(    0, sitePointStatus.getRsp().getCoprocOk()           );
                    assertEquals(    0, sitePointStatus.getRsp().getSurveyFailed()       );
                    assertEquals(    1, sitePointStatus.getRsp().getSurveyPositionValid());
                    assertEquals(    0, sitePointStatus.getRsp().getSurveyInProgress()   );
                    assertEquals(    0, sitePointStatus.getRsp().getHeadingFlag()        );
                    assertEquals(    1, sitePointStatus.getRsp().getPosValid()           );
                    assertEquals(    1, sitePointStatus.getRsp().getValidTime()          );

                    //  getRsp().getMode()
                    assertEquals( 0,sitePointStatus.getRsp().getMode().getFixedBaseEnable() );
                    assertEquals( 0,sitePointStatus.getRsp().getMode().getAutoSurveyEnable());
                    assertEquals( 1,sitePointStatus.getRsp().getMode().getConnected()       );
                    assertEquals( 0,sitePointStatus.getRsp().getMode().getCorrectionOutput());
                    assertEquals( 0,sitePointStatus.getRsp().getMode().getCorrectionInput() );
                    assertEquals( 0,sitePointStatus.getRsp().getMode().getTripodCorrections());
                    assertEquals( 1,sitePointStatus.getRsp().getMode().getValidOrNotBusy()  );
                    assertEquals( 0,sitePointStatus.getRsp().getMode().getBroadcastEnable() );

                    assertEquals(0, sitePointStatus.getReserved2());

//                    delegate.receive(new Status(sitePointStatus));
                } else if (SQTP_ID_SITEPOINT_LLA.equals(messageId)) {
                    byte[] llaData = getFrameReaderSubFrameBytes(reader, myRXFrameMessage);
                    SqspLla_t sitePointLla = sqspLlaCopy(llaData);

                    assertEquals(330891, sitePointLla.getITOW());
                    assertEquals(0, sitePointLla.getReserved());
                    assertEquals(-72.174282 , sitePointLla.getLon()   , 0.0000001);
                    assertEquals( 43.412668 , sitePointLla.getLat()   , 0.0000001);
                    assertEquals(109.500    , sitePointLla.getHeight(), 0.0001);
                    assertEquals(  5.0      , sitePointLla.getHMSL()  , 0.0001);
                    assertEquals(  5.0      , sitePointLla.getHAcc()   , 0.01);
                    assertEquals( 10.0      , sitePointLla.getVAcc()   , 0.01);
//                    delegate.receive(new Location(sitePointLla));
                } else if ((Arrays.asList(SQTP_ID_SITEPOINT_LOCAL_BASE_CONFIG, SQTP_ID_SITEPOINT_RELPOS).contains(messageId))) {
//                    Log.d(LOG_TAG, "Not yet handled message type: '{" + messageId + "}'");
                    byte[] ConfigData = getFrameReaderSubFrameBytes(reader, myRXFrameMessage);
                    SqspLbConfig_t sitePointLbConfig = sqspLbConfigCopy(ConfigData);

                    assertEquals(-72.174282 , sitePointLbConfig.getLon()   , 0.0000001);
                    assertEquals( 43.412668 , sitePointLbConfig.getLat()   , 0.0000001);
                    assertEquals(109.500    , sitePointLbConfig.getHeight(), 0.0001);
                    assertEquals(  5.0      , sitePointLbConfig.getHMSL()  , 0.0001);
                    assertEquals(  5.0      , sitePointLbConfig.getHAcc()   , 0.01);
                    assertEquals( 10.0      , sitePointLbConfig.getVAcc()   , 0.01);
                    assertEquals(  0      , sitePointLbConfig.getDuration());

                    assertEquals( 0, sitePointLbConfig.getMode().getFixedBaseEnable()  );
                    assertEquals( 0, sitePointLbConfig.getMode().getAutoSurveyEnable() );
                    assertEquals( 1, sitePointLbConfig.getMode().getConnected()        );
                    assertEquals( 0, sitePointLbConfig.getMode().getCorrectionOutput() );
                    assertEquals( 0, sitePointLbConfig.getMode().getCorrectionInput()  );
                    assertEquals( 0, sitePointLbConfig.getMode().getTripodCorrections());
                    assertEquals( 1, sitePointLbConfig.getMode().getValidOrNotBusy()   );
                    assertEquals( 0, sitePointLbConfig.getMode().getBroadcastEnable()  );

                    assertEquals(""     , sitePointLbConfig.getReserved());
//                    delegate.receive(new Status(sitePointStatus));
                } else {
                    Log.d(LOG_TAG, "Unsupported message type: '{" + messageId + "}'");
                }
                reader = sqtpFrameReaderNextStruct(reader);
            }

            if (reader.getStatus() != SqtpStatus_t.SQTP_STATUS_FRAME_END) {
                String statusText = reader.getStatus().toString();
                String detail = statusText + ", frame index " + reader.getIndex() + ", subframe id " + reader.getSubframe().getId() + ", payload length " + reader.getSubframe().getLength();
//                throw new SdkException("Frame parsing error: " + detail);
            }
        }

    private byte[] getFrameReaderSubFrameBytes(SqtpFrameReader_t reader, ByteBuffer BBuffer)
    {
        // Extract our Sub Frame Message (not including the trailing SqtpCheckValue_t (4 byte CRC)
        // which we have to account for here).

        byte[] Values = new byte[(int)reader.getSubframe().getLength()];

        //TODO: Add some hardening and checks for absurd values, etc. here:
        System.arraycopy(BBuffer.array(), ((int) reader.getIndex() - SqtpCheckValue_tSize() - Values.length), Values, 0, Values.length);

        return (Values);
    }
}


//-------------------------------
//Time Test Data:
//-------------------------------
//From:   https://gnsscalc.com/
//-------------------------------
//
//        Initial Time
//        -------------------------
//        Week no.		    2309
//        Time of week	    330891
//        GPS Time		    1396814091
//        GAL Time		    777498891
//        BDS Time		    576705291
//        UNIX Time		    1712778891
//        GLO N4			7
//        GLO NA			101
//        Day of Year		101
//        Week of Year	    15
//        Time of Day		71691
//        Day of Week		3
//        Hour Code		    t
//        Julian Date		2460411.329759
//        MJD				60410.830
//        MJD2000			8866.830
//        Leap Sec.		    37 [TAI], 18 [GPS]
//        Date [TAI]		2024-04-10
//        Time [TAI]		19:55:28
//        Date [TT]		    2024-04-10
//        Time [TT]		    19:56:00.350
//        Date [UTC]		2024-04-10
//        Time [UTC]		19:54:51
//        RINEX			    > 2024 04 10 19 54 51.1660000

