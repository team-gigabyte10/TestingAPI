package com.example.testingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testingapi.Helper.PreferencesHelper;
import com.example.testingapi.Interface.ApiService;
import com.example.testingapi.Model.PasswordModel;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    private String u_Name, c_Pass, n_Pass, o_Pass, u_token;
    private EditText current_Pass;
    private EditText new_Pass;
    private EditText con_Pass;

    private Button btn_Conform;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        current_Pass = findViewById(R.id.old_pass);
        new_Pass = findViewById(R.id.new_password);
        con_Pass = findViewById(R.id.com_password);
        btn_Conform = findViewById(R.id.b_conform);
        Paper.init(this);

        PreferencesHelper preferencesHelper = new PreferencesHelper(ChangePassword.this);
        String token = preferencesHelper.getToken();
        String user = preferencesHelper.getUser();

        btn_Conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                u_token = token;
                u_Name = user;
                o_Pass = current_Pass.getText().toString();
                n_Pass = new_Pass.getText().toString();
                c_Pass = con_Pass.getText().toString();

                PasswordModel passwordModel = new PasswordModel(
                        u_Name,
                        o_Pass,
                        n_Pass,
                        c_Pass
                );
                ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

                //ProcessData(u_token,u_Name, o_Pass, n_Pass, c_Pass);
                Call<PasswordModel> changePasswordCall = apiService.updatePassword(
                        u_token,
                        passwordModel.getUserID(),
                        passwordModel.getCurrentPassword(),
                        passwordModel.getRetypePassword(),
                        passwordModel.getNewPassword()
                );

                changePasswordCall.enqueue(new Callback<PasswordModel>() {
                    @Override
                    public void onResponse(Call<PasswordModel> call, Response<PasswordModel> response) {

                        if (response.isSuccessful()) {
                            PasswordModel changedPassword = response.body();
                            int statusCode = Integer.parseInt(changedPassword.getStatusCode());
                            if (statusCode == 1){
                                Toast.makeText(ChangePassword.this, changedPassword.getMessage(), Toast.LENGTH_LONG).show();
                                Paper.book().destroy();
                                Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(ChangePassword.this, changedPassword.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(ChangePassword.this, "Failed", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PasswordModel> call, Throwable t) {
                        Toast.makeText(ChangePassword.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}