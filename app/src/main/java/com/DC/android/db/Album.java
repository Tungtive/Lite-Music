package com.DC.android.db;

/**
 * Created by XZJ on 2017/12/17.
 */

public class Album {
    private String name;
    private String picUrl;
    private String publishTime;
    private String company;

    public Album() {
    }

    public Album(String name, String picUrl, String publishTime, String company) {
        this.name = name;
        this.picUrl = picUrl;
        this.publishTime = publishTime;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
