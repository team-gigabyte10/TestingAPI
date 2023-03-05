package com.example.testingapi.Interface;

import com.example.testingapi.Model.LoginRequest;
import com.example.testingapi.Model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("secuirity/loginMobile")
    Call<LoginResponse> loginUser(@Field("UserID") String userId, @Field("Password") String password);


}
