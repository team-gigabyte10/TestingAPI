package com.example.testingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testingapi.Constants.Common;
import com.example.testingapi.Constants.Constant;
import com.example.testingapi.Helper.PreferencesHelper;
import com.example.testingapi.Interface.ApiInterface;
import com.example.testingapi.Model.LoginResponse;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private TextView Token;
    private Button btnLogin;
    CheckBox checkBox;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);

        Token = findViewById(R.id.token);
        etUsername = (EditText) findViewById(R.id.u_name);
        etPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.b_login);
        checkBox = (CheckBox) findViewById(R.id.ckbRemember);

        String usr = Paper.book().read(Common.USER_KEY);
        String pwd = Paper.book().read(Common.PWD_KEY);

        if(usr != null && pwd != null){

            if(!usr.isEmpty() && !pwd.isEmpty()){

                login(usr,pwd);
            }
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (user.isEmpty()) {
                    etUsername.setError("Email is required");
                    etUsername.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    etPassword.setError("Password is required");
                    etPassword.requestFocus();
                    return;
                }
                if (Common.isConnectedToInternet(getBaseContext())) {

                    login(user, password);
                }else
                {
                    Toast.makeText(MainActivity.this, "Please Check Your Net Connection !!", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    private void login(String userid, String password) {

        LoginResponse loginResponse = new LoginResponse(
                userid,
                password
        );

        ApiInterface apiService = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<LoginResponse> call = apiService.loginUser(
                loginResponse.getUserID(),
                loginResponse.getPassword()
        );

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    LoginResponse loginResponse = response.body();
                    int statusCode = Integer.parseInt(loginResponse.getStatusCode());


                    if (statusCode== 1) {
                        if (checkBox.isChecked()) {

                            Paper.book().write(Common.USER_KEY, userid);
                            Paper.book().write(Common.PWD_KEY, password);
                        }
                        String token = response.body().getToken();
                        String userId = userid;
                        PreferencesHelper preferencesHelper = new PreferencesHelper(MainActivity.this);
                        preferencesHelper.saveToken(token);
                        preferencesHelper.saveUser(userId);
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Homepage.class);
                        startActivity(intent);
                        finish();

                    }
                    else {

                        Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Not Access", Toast.LENGTH_LONG).show();
            }
        });
    }

}