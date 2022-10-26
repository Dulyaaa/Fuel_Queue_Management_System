package com.example.fuelqueue.remote;

import com.example.fuelqueue.model.FuelStation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Web service urls of fuel station for each function
 */
public interface FuelStationService {

    /**
     * Get all fuel station details
     *
     * @return status code with response
     */
    @GET("/api/Shed/GetAll")
    Call<List<FuelStation>> getAllFuelStations();

    /**
     * Create new fuel station
     *
     * @param fuelStation FuelStation object
     * @return status code with response
     */
    @POST("/api/Shed/Save")
    Call<FuelStation> addNewFuelStation(@Body FuelStation fuelStation);

    /**
     * Update existing fuel station record
     *
     * @param id          object id
     * @param fuelStation FuelStation object
     * @return status code with response
     */
    @PUT("/api/Shed/Update/{id}")
    Call<FuelStation> UpdateFuelStation(@Path("id") int id, @Body FuelStation fuelStation);

    /**
     * Delete fuel queue record
     *
     * @param id object id
     * @return status code with response
     */
    @DELETE("/api/Shed/Delete/{id}")
    Call<FuelStation> deleteFuelStation(@Path("id") int id);
}
