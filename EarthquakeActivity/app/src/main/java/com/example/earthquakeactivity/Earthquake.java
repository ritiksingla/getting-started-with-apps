package com.example.earthquakeactivity;

public class Earthquake {
    private double magnitude;
    private String location;
    private long mTimeInMilliSeconds;
    private String mUrl;

    public Earthquake(double magnitude, String location, long timeInMilliSeconds,String Url){
        this.magnitude = magnitude;
        this.location = location;
        mTimeInMilliSeconds=timeInMilliSeconds;
        mUrl=Url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTimeInMilliSeconds() {
        return mTimeInMilliSeconds;
    }
    public String getUrl() { return mUrl; }

}
