package com.example.testingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.testingapi.Helper.PreferencesHelper;
import com.example.testingapi.Interface.ApiService;

public class SecondActivity extends AppCompatActivity {

    private TextView Token, Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Token = findViewById(R.id.token);
        Data = findViewById(R.id.data);

        PreferencesHelper preferencesHelper = new PreferencesHelper(SecondActivity.this);
        String token = preferencesHelper.getToken();

        Token.setText(token);
        // Make the API call using Retrofit
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

//        Call<List<DataModel>> call = apiService.getData(token); //ResponseObj
//        call.enqueue(new Callback<List<DataModel>>() {
//            @Override
//            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
//                if (response.isSuccessful()) {
//                    List<DataModel> myDataList = response.body();
//                    for (int i=0; i<myDataList.size(); i++) {
//                         Data.append("List: " +myDataList);
//                    }
//                    // Data.append("List: " +myDataList);
//                } else {
//                    Data.setText("Data Not Found");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DataModel>> call, Throwable t) {
//                Data.setText("Error is Occurred");
//            }
//        });

    }
}