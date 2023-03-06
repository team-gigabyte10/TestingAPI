package com.example.testingapi.Model;

public class DataModel {
String Name, Code;
String ResponseObj;

    public DataModel() {
    }

    public DataModel(String name, String code) {
        Name = name;
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getResponseObj() {
        return ResponseObj;
    }

    public void setResponseObj(String responseObj) {
        ResponseObj = responseObj;
    }
}
