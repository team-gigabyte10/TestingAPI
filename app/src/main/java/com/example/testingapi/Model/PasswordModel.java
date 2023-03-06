package com.example.testingapi.Model;

import com.google.gson.annotations.SerializedName;

public class PasswordModel {

    String UserID, CurrentPassword, NewPassword, RetypePassword;

    String Message;
    @SerializedName("StatusCode")
    String StatusCode;

    public PasswordModel() {
    }

    public PasswordModel(String message) {
        Message = message;
    }

    public PasswordModel(String userID, String currentPassword, String newPassword, String retypePassword) {
        UserID = userID;
        CurrentPassword = currentPassword;
        NewPassword = newPassword;
        RetypePassword = retypePassword;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCurrentPassword() {
        return CurrentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        CurrentPassword = currentPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getRetypePassword() {
        return RetypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        RetypePassword = retypePassword;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }
}
