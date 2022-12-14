package com.example.fuelqueue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * FuelStation model
 */
public class FuelStation {
    @SerializedName("id")
    @Expose
    public String Id;

    @SerializedName("userId")
    @Expose
    public String userId;

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

    public FuelStation(String id, String userId, String stationName, String city, int queueLength, String avgTime) {
        Id = id;
        this.userId = userId;
        this.stationName = stationName;
        this.city = city;
        this.queueLength = queueLength;
        this.avgTime = avgTime;
    }

    public FuelStation() {

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}