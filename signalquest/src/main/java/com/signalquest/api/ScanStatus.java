package com.signalquest.api;

import static com.signalquest.swig.sqsp.sqsp.sqspRspCCopy;
import static com.signalquest.swig.sqsp.sqsp.sqspRspExpand;

import com.signalquest.swig.sqsp.SqspRspC_t;
import com.signalquest.swig.sqsp.SqspRsp_t;

/**
 * Contains the BLE scanning information.
 */
@SuppressWarnings("unused")
public class ScanStatus {
    private final boolean connected;
    private final int battery;
    private final boolean charging;
    private final int satellites;

    /**
     * Instantiates a new ScanStatus.
     *
     * @param manufacturerData The sensor's manufacturer data from the BLE advertisement
     */
    public ScanStatus(byte[] manufacturerData) {
        byte[] signalQuestId = {55, 12}; // i.e. 3127 or 0x370C
        byte[] joined = new byte[signalQuestId.length + manufacturerData.length];
        System.arraycopy(signalQuestId,0, joined,0, signalQuestId.length);
        System.arraycopy(manufacturerData,0, joined, signalQuestId.length, manufacturerData.length);

        SqspRsp_t rsp = new  SqspRsp_t();
        SqspRspC_t compact = sqspRspCCopy(joined);
        boolean result = sqspRspExpand(rsp, compact);
        assert(result);

        connected = rsp.getMode().getConnected() != 0;
        battery = rsp.getBatteryLevel();
        satellites = rsp.getSatelliteCount();
        charging = isCharging(rsp.getSystemPowerState());
    }

    private static boolean isCharging(int systemPowerState) {
        int chargingBit = 6;
        return (systemPowerState & (1 << chargingBit)) != 0;
    }

    /**
     * Whether a sensor is already connected to another device
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Battery percentage remaining, 0-100
     */
    public int getBattery() {
        return battery;
    }

    /**
     * Whether a sensor is actively charging
     */
    public boolean isCharging() {
        return charging;
    }

    /**
     * The number of satellites used to calculate the location solution
     */
    public int getSatellites() {
        return satellites;
    }
}

