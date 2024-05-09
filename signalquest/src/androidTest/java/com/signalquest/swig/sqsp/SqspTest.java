package com.signalquest.swig.sqsp;

import static com.signalquest.swig.sqsp.sqsp.SqspLbConfig_tByteArray;
import static com.signalquest.swig.sqsp.sqsp.SqspLbConfig_tSize;
import static com.signalquest.swig.sqsp.sqsp.SqspLla_tByteArray;
import static com.signalquest.swig.sqsp.sqsp.SqspLla_tSize;
import static com.signalquest.swig.sqsp.sqsp.SqspStatus_tByteArray;
import static com.signalquest.swig.sqsp.sqsp.SqspStatus_tSize;
import static com.signalquest.swig.sqsp.sqsp.sqspLbConfigCompact;
import static com.signalquest.swig.sqsp.sqsp.sqspLbConfigExpand;
import static com.signalquest.swig.sqsp.sqsp.sqspLbModeCCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspLbModeCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspLbConfigCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspLlaCompact;
import static com.signalquest.swig.sqsp.sqsp.sqspLlaCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspRspCCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspRspCInit;
import static com.signalquest.swig.sqsp.sqsp.sqspRspExpand;
import static com.signalquest.swig.sqsp.sqsp.sqspSolutionTypeLabel;
import static com.signalquest.swig.sqsp.sqsp.sqspSolutionTypeLabelCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusCCreate;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusCopyCast;
import static com.signalquest.swig.sqsp.sqsp.sqspStatusExpand;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4.class)
public class SqspTest {
    @Test
    public void sqspRspCCopy_test() {

        System.loadLibrary("sqsp_wrapper"); // We need this to generate test data and decode test data.
//        System.loadLibrary("sqtp_wrapper");

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
    public void sqsp_initial_test() {
        System.loadLibrary("sqsp_wrapper");

        byte[] myStatusSubFrame = null;
        byte[] myLLASubFrame = null;
        byte[] myConfigSubFrame = null;

        short EXPECTED_MFG_DATA_ID = 0x0C37; //TODO: FIXME //#define EXPECTED_MFG_DATA_ID 0x0C37	//0x02E5  //  <Espressif ID, (2 bytes: 0x02E5)>	//	Should be 0x0C37 SignalQuest, LLC (aka 3127)
        boolean result;
        int myResult = 0;

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

        //-----------------------------------------------------------------------------------------
        // Test the Byte[] converters
        //-----------------------------------------------------------------------------------------

        //  This is addressed in the separate test sqspRspCCopy_test() above.
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

////////////////////////////////////////////
//        SqspStatus_t test = sqspStatusCopyCast( myBytes );
//
//        assertEquals(test.getITOW(), 330891);
//        assertEquals(test.getTime(), 1712793328);
//        assertEquals(test.getSolType(), 14);
//        assertEquals(test.getNumSV(), 4);
//        assertEquals(test.getBattery(), 255);
//        assertEquals(test.getReserved(), 0);
//
//        // getRsp()
//        assertEquals(test.getRsp().getMfgDataId(), 3127);
//        assertEquals(test.getRsp().getBatteryLevel(), 93);
//        assertEquals(test.getRsp().getSystemPowerState(), 1);
//        assertEquals(test.getRsp().getRunTimeRemaining(), 1234);
//        assertEquals(test.getRsp().getSatelliteCount(), 2);
//        assertEquals(test.getRsp().getAidingBins(), 15);
//        assertEquals(test.getRsp().getDifferential(), 0);
//        assertEquals(test.getRsp().getGpsFixOk(), 0);
//        assertEquals(test.getRsp().getAmbiguity(), 0);
//        assertEquals(test.getRsp().getFix(), 0);
//        assertEquals(test.getRsp().getCoprocOk(), 0);
//        assertEquals(test.getRsp().getSurveyFailed(), 0);
//        assertEquals(test.getRsp().getSurveyPositionValid(), 1);
//        assertEquals(test.getRsp().getSurveyInProgress(), 0);
//        assertEquals(test.getRsp().getHeadingFlag(), 0);
//        assertEquals(test.getRsp().getPosValid(), 1);
//        assertEquals(test.getRsp().getValidTime(), 1);
//
//        //  getRsp().getMode()
//        assertEquals(test.getRsp().getMode().getFixedBaseEnable(), 0);
//        assertEquals(test.getRsp().getMode().getAutoSurveyEnable(), 0);
//        assertEquals(test.getRsp().getMode().getConnected(), 1);
//        assertEquals(test.getRsp().getMode().getCorrectionOutput(), 0);
//        assertEquals(test.getRsp().getMode().getCorrectionInput(), 0);
//        assertEquals(test.getRsp().getMode().getTripodCorrections(), 0);
//        assertEquals(test.getRsp().getMode().getValidOrNotBusy(), 1);
//        assertEquals(test.getRsp().getMode().getBroadcastEnable(), 0);
//
//        assertEquals(test.getReserved2(), 0);
////////////////////////////////////////////

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

