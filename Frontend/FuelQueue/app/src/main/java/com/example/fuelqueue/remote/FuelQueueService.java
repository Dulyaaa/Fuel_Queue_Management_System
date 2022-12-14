package com.example.fuelqueue.remote;

import com.example.fuelqueue.model.FuelQueue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Web service urls of fuel queue for each function
 */
public interface FuelQueueService {
    /**
     * Get all fuel queue details
     *
     * @return status code with response
     */
    @GET("/GetAllUserQueue")
    Call<List<FuelQueue>> getAllFuelQueue();

    /**
     * Create new fuel queue record
     *
     * @param fuelQueue FuelQueue object
     * @return status code with response
     */
    @POST("/SaveUserQueue")
    Call<FuelQueue> addFuelQueue(@Body FuelQueue fuelQueue);

    /**
     * Update existing fuel queue record
     *
     * @param id        object id
     * @return status code with response
     */
    @POST("/UpdateUserFuelQueue")
    Call<Void> updateFuelQueue(@Query("QueueId") String id, @Query("Status") String status, @Query("DepartureTime") String departureTime);

    /**
     * Delete fuel queue record
     *
     * @param id object id
     * @return status code with response
     */
    @DELETE("delete/{id}")
    Call<FuelQueue> deleteFuelQueue(@Path("id") int id);
}
