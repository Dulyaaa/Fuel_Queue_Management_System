package com.example.fuelqueue.config;

import com.example.fuelqueue.service.IUserApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionService {
    public static IUserApi Create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IUserApi.class);
    }
}
