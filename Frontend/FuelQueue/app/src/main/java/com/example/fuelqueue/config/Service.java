//package com.example.fuelqueue.config;
//
//import com.example.fuelqueue.service.IFuelStationService;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class Service {
//    public static IFuelStationService FuelStationCreate(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:5000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit.create(IFuelStationService.class);
//    }
//}
