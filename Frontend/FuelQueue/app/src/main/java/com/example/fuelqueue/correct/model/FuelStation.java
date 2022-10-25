package com.example.fuelqueue.correct.model;

import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelStation {
    @SerializedName("shedId")
    @Expose
    public String Id;

    @SerializedName("shedName")
    @Expose
    public String stationName;

    @SerializedName("city")
    @Expose
    public String city;

    @SerializedName("queueLength")
    @Expose
    public int queueLength;

    @SerializedName("avgTime")
    @Expose
    public String avgTime;

    public FuelStation(String id, String stationName, String city, int queueLength, String avgTime) {
        Id = id;
        this.stationName = stationName;
        this.city = city;
        this.queueLength = queueLength;
        this.avgTime = avgTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }
}