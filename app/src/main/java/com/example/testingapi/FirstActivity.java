package com.example.testingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testingapi.Helper.PreferencesHelper;

public class FirstActivity extends AppCompatActivity {

    private Button btn_Change;
    private TextView Token;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Token =findViewById(R.id.text);

//        PreferencesHelper preferencesHelper = new PreferencesHelper(FirstActivity.this);
//        String token = preferencesHelper.getToken();
//
//        Token.setText(token);

        btn_Change = findViewById(R.id.btn_Press);
        btn_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}