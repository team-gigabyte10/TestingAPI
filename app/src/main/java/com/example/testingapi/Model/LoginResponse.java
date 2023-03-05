package com.example.testingapi.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("Token")
    private String token;

    @SerializedName("StatusCode")
    String StatusCode;
    private String UserID, Password;
    private String Message;

    public LoginResponse() {
    }

    public LoginResponse(String userID, String password) {
        UserID = userID;
        Password = password;
    }


    public LoginResponse(String message) {
        Message = message;
    }

    @SerializedName("success")
    private boolean success;
    public boolean isSuccess() {
        return success;
    }
    //////////////////////////////////////////


    // getters and setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}