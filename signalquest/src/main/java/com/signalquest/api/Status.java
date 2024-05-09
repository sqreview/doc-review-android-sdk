package com.signalquest.api;

import static com.signalquest.swig.sqsp.sqsp.sqspSolutionTypeLabel;

import com.signalquest.swig.sqsp.SqspRsp_t;
import com.signalquest.swig.sqsp.SqspStatus_t;

import java.util.ArrayList;
import java.util.List;


/**
 * Contains the sensor status information.
 */
@SuppressWarnings("unused")
public class Status {
    private final long iTow;
    private final long time;
    private final int mode;
    private final int satellites;
    private final int battery;
    private final boolean charging;
    private final boolean[] aidingQuality;

    Status(SqspStatus_t s) {
        iTow = s.getITOW();
        time = s.getTime();
        mode = s.getSolType();
        satellites = s.getNumSV();
        final SqspRsp_t rsp = s.getRsp();
        battery = rsp.getBatteryLevel();
        charging = isCharging(rsp.getSystemPowerState());
        aidingQuality = getAidingQualityBins(rsp.getAidingBins());
    }

    /**
     * Constructor for serialization (e.g., Android Parcelable)
     */
    public Status(long iTow, long time, int mode, int satellites, int battery, boolean charging, boolean[] aidingQuality) {
        this.iTow = iTow;
        this.time = time;
        this.mode = mode;
        this.satellites = satellites;
        this.battery = battery;
        this.charging = charging;
        this.aidingQuality = aidingQuality;
    }

    private static boolean[] getAidingQualityBins(int aidingQuality) {
        boolean[] out = new boolean[8];
        int values = aidingQuality;
        for (int i = 0; i < 8; i++) {
            out[i] = (values & 0x01) == 1;
            values >>= 1;
        }
        return out;
    }

    private static boolean isCharging(int systemPowerState) {
        int chargingBit = 6;
        return (systemPowerState & (1 << chargingBit)) != 0;
    }

    /**
     * The GPS time of week, in milliseconds.
     */
    public long getITow() {
        return iTow;
    }

    /**
     * The Unix time, in seconds.
     */
    public long getTime() {
        return time;
    }

    /**
     * The GNSS operational mode or solution state.
     *
     * <p>&nbsp;</p>
     * <table border="1">
     *     <caption>
     *         Mode Values
     *     </caption>
     *     <tr>
     *         <th>Value</th>
     *         <th>Mode</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>Offline</td>
     *     </tr>
     *     <tr>
     *         <td>1</td>
     *         <td>Acquiring</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>2D</td>
     *     </tr>
     *     <tr>
     *         <td>3</td>
     *         <td>3D</td>
     *     </tr>
     *     <tr>
     *         <td>4</td>
     *         <td>RTK Float</td>
     *     </tr>
     *     <tr>
     *         <td>5</td>
     *         <td>RTK Fixed</td>
     *     </tr>
     *     <tr>
     *         <td>6</td>
     *         <td>Base Autosurvey</td>
     *     </tr>
     *     <tr>
     *         <td>7</td>
     *         <td>Fixed Base</td>
     *     </tr>
     * </table>
     */
    public int getMode() {
        return mode;
    }

    /**
     * The number of satellites used to calculate location solution.
     */
    public int getSatellites() {
        return satellites;
    }

    /**
     * The battery percentage remaining, 0-100.
     */
    public int getBattery() {
        return battery;
    }

    /**
     * Indicates whether the sensor is actively charging.
     */
    public boolean isCharging() {
        return charging;
    }

    /**
     * Reports which of the 8 latest RTCM messages were used.
     */
    public boolean[] getAidingQuality() {
        return aidingQuality;
    }

    /**
     * Provides the text label for {@link #getMode()}
     */
    public String getModeLabel() {
        String label = sqspSolutionTypeLabel((char) mode);
        return label != null ? label : "Unknown";
    }
}
