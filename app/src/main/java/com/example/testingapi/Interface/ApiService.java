package com.example.testingapi.Interface;

import com.example.testingapi.Model.DataModel;
import com.example.testingapi.Model.PasswordModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("Village/getThanaCircleInitialData")
    Call<List<DataModel>> getData(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("secuirity/changePasswordMobile")
    Call<PasswordModel> updatePassword(
            @Header("Authorization") String token,
            @Field("UserID") String userId,
            @Field("CurrentPassword") String password,
            @Field("NewPassword") String newPassword,
            @Field("RetypePassword") String retypePassword
    );

}
