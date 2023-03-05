package com.example.testingapi.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testingapi.Constants.Constant;

public class PreferencesHelper {

    private SharedPreferences mSharedPreferences;

    public PreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
    }
    public void saveUser(String userId) {
        mSharedPreferences.edit().putString(Constant.PREF_USER, userId).apply();
    }

    public void saveToken(String token) {
        mSharedPreferences.edit().putString(Constant.PREF_TOKEN, token).apply();
    }

    public String getUser() {
        return mSharedPreferences.getString(Constant.PREF_USER, null);
    }

    public String getToken() {
        return mSharedPreferences.getString(Constant.PREF_TOKEN, null);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
