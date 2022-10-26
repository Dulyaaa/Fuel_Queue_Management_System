package com.example.fuelqueue.remote;

import com.example.fuelqueue.model.FuelStock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Web service urls of fuel stock for each function
 */
public interface FuelStockService {
    /**
     * Get all fuel stock details
     *
     * @return status code with response
     */
    @GET("/GetAllFuelStock")
    Call<List<FuelStock>> getAllFuelStock();

    /**
     * Create new fuel stock record
     *
     * @param fuelStock FuelStock object
     * @return status code with response
     */
    @POST("/SaveFuelStock")
    Call<FuelStock> addNewFuelStock(@Body FuelStock fuelStock);

    /**
     * Update existing fuel stock record
     *
     * @param id        object id
     * @param fuelStock FuelStock object
     * @return status code with response
     */
    @PUT("/UpdateFuelStock/{id}")
    Call<FuelStock> updateFuelStock(@Path("id") int id, @Body FuelStock fuelStock);

    /**
     * Delete fuel stock record
     *
     * @param id object id
     * @return status code with response
     */
    @DELETE("/Delete/FuelStock/{id}")
    Call<FuelStock> deleteFuelStock(@Path("id") int id);
}
