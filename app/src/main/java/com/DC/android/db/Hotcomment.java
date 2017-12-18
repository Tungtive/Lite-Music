package com.DC.android.db;

/**
 * Created by XZJ on 2017/12/17.
 */

public class Hotcomment {
    private  String userAvaturUrl;
    private  String userNickName;
    private  String content;
    private  long likedCount;
    private  String time;

    public String getUserAvaturUrl() {
        return userAvaturUrl;
    }

    public void setUserAvaturUrl(String userAvaturUrl) {
        this.userAvaturUrl = userAvaturUrl;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(long likedCount) {
        this.likedCount = likedCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Hotcomment() {
    }
}
