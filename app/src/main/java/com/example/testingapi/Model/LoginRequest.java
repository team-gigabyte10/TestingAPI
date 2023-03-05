package com.example.testingapi.Model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("UserID")
    private final String UserID;

    @SerializedName("Password")
    private final String Password;

    public LoginRequest(String userID, String password) {
        UserID = userID;
        Password = password;
    }

    private boolean success;
    public boolean isSuccess() {
        return success;
    }
    //////////////////////////////////////////
    @SerializedName("CurrentPassword ")
    private String CurrentPassword ;
    @SerializedName("NewPassword")
    private String NewPassword;
    @SerializedName("RetypePassword")
    private String RetypePassword;

    @SerializedName("Token")
    private String token;

    @SerializedName("UserID")
    private String userId;

    // getters and setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


//    public String getPassword() {
//        return Password;
//    }
}
