package com.example.fuelqueue.service;

import com.example.fuelqueue.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IUserApi {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}/users")
    Call<List<User>> getUserById(@Path("id") int id);
}
