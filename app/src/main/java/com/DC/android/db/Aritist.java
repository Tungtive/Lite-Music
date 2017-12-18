package com.DC.android.db;

/**
 * Created by XZJ on 2017/12/17.
 */

public class Aritist {
    private String  name;
    private String  picUrl;

    public Aritist(String name, String picUrl) {
        this.name = name;
        this.picUrl = picUrl;
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
}
