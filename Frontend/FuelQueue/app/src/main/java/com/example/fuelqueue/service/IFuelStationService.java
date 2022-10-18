package com.example.fuelqueue.service;

import com.example.fuelqueue.model.FuelStation;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IFuelStationService {
    @GET("/api/fuelStation")
    public void getFuelStations(Callback<List<FuelStation>> callback);

    @GET("/api/fuelStation/{id}")
    public void getFuelStationById(@Path("id") int id, Callback<List<FuelStation>> callback);

    @POST("/api/fuelStation")
    public void addFuelStation(@Body FuelStation fuelStation, Callback<List<FuelStation>> callback);

    @PUT("/api/fuelStation/{id}")
    public void updateFuelStation(@Path("id") int id, Callback<List<FuelStation>> callback);

    @DELETE("/api/fuelStation/{id}")
    public void deleteFuelStation(@Path("id") int id, Callback<List<FuelStation>> callback);
}
