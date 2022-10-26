package com.example.fuelqueue.db;

import androidx.annotation.NonNull;

import com.example.fuelqueue.remote.FuelQueueService;
import com.example.fuelqueue.remote.FuelStationService;
import com.example.fuelqueue.remote.FuelStockService;
import com.example.fuelqueue.remote.UserService;


/**
 * Get connection with server for each service
 */
public class APIUtils {

    /* Server url declaration */
    public static final String API_URL = "http://192.168.8.134:5000";

    /**
     * Establishing user service connection
     *
     * @return connection status
     */
    @NonNull
    public static UserService getUserConnection() {
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

    /**
     * Establishing fuel station service connection
     *
     * @return connection status
     */
    @NonNull
    public static FuelStationService getFuelStationConnection() {
        return RetrofitClient.getClient(API_URL).create(FuelStationService.class);
    }

    /**
     * Establishing fuel queue service connection
     *
     * @return connection status
     */
    @NonNull
    public static FuelQueueService getFuelQueueConnection() {
        return RetrofitClient.getClient(API_URL).create(FuelQueueService.class);
    }

    /**
     * Establishing fuel stock service connection
     *
     * @return connection status
     */
    @NonNull
    public static FuelStockService getFuelStockConnection() {
        return RetrofitClient.getClient(API_URL).create(FuelStockService.class);
    }

}