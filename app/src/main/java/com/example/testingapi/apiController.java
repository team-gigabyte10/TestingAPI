package com.example.testingapi;

import com.example.testingapi.Interface.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiController {

    private static final String url = "http://10.1.0.12:8045/api/";
    private static apiController clientObject;
    private static Retrofit retrofit;

    apiController(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized apiController getInstance()
    {
        if (clientObject == null)
            clientObject = new apiController();
        return clientObject;
    }
    ApiService getApi(){
        return retrofit.create(ApiService.class);
    }
}
