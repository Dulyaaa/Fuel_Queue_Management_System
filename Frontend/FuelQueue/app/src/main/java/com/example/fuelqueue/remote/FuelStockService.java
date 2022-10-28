package com.example.fuelqueue.remote;

import com.example.fuelqueue.model.FuelStock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
     * @param id object id
     * @return status code with response
     */
    @POST("/UpdateFuelStock")
    Call<Void> updateFuelStock(@Query("FuelStockId") String id, @Query("Stock") String stock, @Query("ArrivalTime") String arrivalTime, @Query("FinishTime") String finishTime);

    /**
     * Delete fuel stock record
     *
     * @param id object id
     * @return status code with response
     */
    @DELETE("/Delete/FuelStock/{id}")
    Call<FuelStock> deleteFuelStock(@Path("id") int id);
}
