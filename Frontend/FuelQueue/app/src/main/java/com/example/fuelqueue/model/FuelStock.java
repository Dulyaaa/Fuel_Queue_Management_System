package com.example.fuelqueue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * FuelStock model
 */
public class FuelStock {
    @SerializedName("fuelStockId")
    @Expose
    public String Id;

    @SerializedName("shedId")
    @Expose
    public String shedId;

    @SerializedName("fuelType")
    @Expose
    public String fuelType;

    @SerializedName("arrivalTime")
    @Expose
    public String arrivalTime;

    @SerializedName("finishTime")
    @Expose
    public String finishTime;

    @SerializedName("stock")
    @Expose
    public String stock;

    @SerializedName("status")
    @Expose
    public String status;

    public FuelStock(String id, String shedId, String fuelType, String stock, String arrivalTime, String finishTime, String status) {
        Id = id;
        this.shedId = shedId;
        this.fuelType = fuelType;
        this.stock = stock;
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
        this.status = status;
    }

    public FuelStock() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getShedId() {
        return shedId;
    }

    public void setShedId(String shedId) {
        this.shedId = shedId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
