package com.example.fuelqueue;

import android.app.Application;
import com.example.fuelqueue.service.ApiManager;

public class MainApplication extends Application {
    public static ApiManager apiManager;

    @Override
    public void onCreate(){
        super.onCreate();
        apiManager = ApiManager.getInstance();
    }
}
