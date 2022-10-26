package com.example.fuelqueue.db;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Establishing server connection
 */
public class RetrofitClient {
    /* Variable declaration */
    private static Retrofit retrofit = null;

    /**
     * Establishing server connection
     *
     * @param url String of server url
     * @return status code
     */
    public static Retrofit getClient(String url) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}