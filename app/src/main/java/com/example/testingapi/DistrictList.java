package com.example.testingapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.testingapi.Helper.PreferencesHelper;
import com.example.testingapi.Helper.dataAdapter;
import com.example.testingapi.Interface.ApiService;
import com.example.testingapi.Model.DataModel;
import com.example.testingapi.Model.PasswordModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DistrictList extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<DataModel> all_district_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_list);

        PreferencesHelper preferencesHelper = new PreferencesHelper(DistrictList.this);
        String token = preferencesHelper.getToken();

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<DataModel>> call = apiService.getData(token);
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {

                all_district_data = response.body();


                for (int i=0; i<all_district_data.size(); i++){
                    recyclerView.setAdapter(new dataAdapter(DistrictList.this, all_district_data));
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

            }
        });

    }
}