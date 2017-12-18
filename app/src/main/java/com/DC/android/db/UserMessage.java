package com.DC.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by XZJ on 2017/11/11.
 */

public class UserMessage   extends DataSupport {
     private String name;
     private String email;
     private String region;
     private double balance;
     private String head;
     private int value;
     private  String projectName;
    private  String message;
    private  String timeStamp;



    public UserMessage(){}
    public UserMessage(String name, String email, String region, double balance, String head, int value, String projectName, String message, String timeStamp) {
        this.name = name;
        this.email = email;
        this.region = region;
        this.balance = balance;
        this.head = head;
        this.value = value;
        this.projectName = projectName;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
