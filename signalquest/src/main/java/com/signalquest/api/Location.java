package com.signalquest.api;

import com.signalquest.swig.sqsp.SqspLla_t;

/**
 * Contains the sensor's location information.
 */
@SuppressWarnings("unused")
public class Location {
    private final long iTow;
    private final double latitude;
    private final double longitude;
    private final double height;
    private final double horizontalAccuracy;
    private final double verticalAccuracy;

    /**
     * Instantiates a new Location.
     *
     * @param lla The location data
     */
    Location(SqspLla_t lla) {
        iTow = lla.getITOW();
        latitude = lla.getLat();
        longitude = lla.getLon();
        height = lla.getHeight();
        horizontalAccuracy = lla.getHAcc();
        verticalAccuracy = lla.getVAcc();
    }

    /**
     * Constructor for serialization (e.g. Android Parcelable)
     */
    public Location(long iTow, double latitude, double longitude, double height, double horizontalAccuracy, double verticalAccuracy) {
        this.iTow = iTow;
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
        this.horizontalAccuracy = horizontalAccuracy;
        this.verticalAccuracy = verticalAccuracy;
    }

    /**
     * The GPS time of week, in milliseconds
     */
    public long getITow() {
        return iTow;
    }

    /**
     * The latitude, in decimal degrees (WGS 84)
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * The longitude, in decimal degrees (WGS 84)
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * The height above ellipsoid, in meters
     */
    public double getHeight() {
        return height;
    }

    /**
     * The horizontal accuracy, in meters
     */
    public double getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    /**
     * The vertical accuracy, in meters
     */
    public double getVerticalAccuracy() {
        return verticalAccuracy;
    }
}
