package com.example.fuelqueue.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.fuelqueue.model.User;
import com.example.fuelqueue.service.ApiManager;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static volatile UserRepository userRepository;
    private final ApiManager apiManager;

    private final MutableLiveData<List<User>> users = new MutableLiveData<>();

    private UserRepository(ApiManager apiManager){
        this.apiManager = apiManager;
    }

    public static UserRepository getInstance(ApiManager apiManager) {
        if (userRepository == null) {
            userRepository = new UserRepository(apiManager);
        }
        return userRepository;
    }

    public MutableLiveData<List<User>> getUsers(){
        apiManager.getUsers(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call,
                                   Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> body = response.body();
                    users.setValue(body);
                } else{
                    users.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                users.postValue(null);
            }
        });

        return users;
    }
}
