package com.example.fuelqueue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * FuelQueue model
 */
public class FuelQueue {
    @SerializedName("queueId")
    @Expose
    private String id;

    @SerializedName("shedId")
    @Expose
    private String fuelStationId;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;

    @SerializedName("departureTime")
    @Expose
    private String departureTime;

    @SerializedName("status")
    @Expose
    private String status;

    public FuelQueue() {
    }

    public FuelQueue(String id, String fuelStationId, String userId, String arrivalTime, String departureTime, String status) {
        this.id = id;
        this.fuelStationId = fuelStationId;
        this.userId = userId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelStationId() {
        return fuelStationId;
    }

    public void setFuelStationId(String fuelStationId) {
        this.fuelStationId = fuelStationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
