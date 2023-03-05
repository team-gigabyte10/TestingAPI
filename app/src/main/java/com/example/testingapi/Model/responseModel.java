package com.example.testingapi.Model;

public class responseModel {

    String Message;

    public responseModel() {
    }

    public responseModel(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
