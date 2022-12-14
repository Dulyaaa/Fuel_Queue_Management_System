package com.example.fuelqueue.remote;

import com.example.fuelqueue.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Web service urls of user for each function
 */
public interface UserService {
    /**
     * Get all user details
     *
     * @return status code with response
     */
    @GET("/GetAllUser")
    Call<List<User>> getUsers();

    /**
     * Create new user record
     *
     * @param user User object
     * @return status code with response
     */
    @POST("/SaveUser")
    Call<User> addUser(@Body User user);

    /**
     * Update existing user record
     *
     * @param id   object id
     * @param user User object
     * @return status code with response
     */
    @PUT("/updateUser/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    /**
     * Delete user record
     *
     * @param id object id
     * @return status code with response
     */
    @DELETE("/deleteUser/{id}")
    Call<User> deleteUser(@Path("id") int id);
}
