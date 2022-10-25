package com.example.fuelqueue.correct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelStock {
    @SerializedName("fuelStockId")
    @Expose
    public String Id;

    @SerializedName("shedId")
    @Expose
    public String shedId;

    @SerializedName("fuelTypeId")
    @Expose
    public String fuelType;

//    @SerializedName("stock")
//    @Expose
//    public String stock;

    @SerializedName("arrivalTime")
    @Expose
    public String arrivalTime;

    @SerializedName("finishTime")
    @Expose
    public String finishTime;

    public FuelStock(String id, String shedId, String fuelType, String arrivalTime, String finishTime) {
        Id = id;
        this.shedId = shedId;
        this.fuelType = fuelType;
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
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
}
