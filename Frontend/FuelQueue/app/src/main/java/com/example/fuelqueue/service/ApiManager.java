package com.example.fuelqueue.service;

import com.example.fuelqueue.config.ConnectionService;
import com.example.fuelqueue.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class ApiManager {
    private static IUserApi service;
    private static ApiManager apiManager;

    private ApiManager(){
        service = ConnectionService.Create();
    }

    public static ApiManager getInstance(){
        if(apiManager == null){
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public void getUsers(Callback<List<User>> callback){
        Call<List<User>> userCall = service.getUsers();
        userCall.enqueue(callback);
    }
}
