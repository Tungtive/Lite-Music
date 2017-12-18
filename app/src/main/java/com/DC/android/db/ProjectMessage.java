package com.DC.android.db;

/**
 * Created by XZJ on 2017/11/13.
 */

public class ProjectMessage {
    private String userHead;
    private double donateMoney;
    private String userName;
    private String timestamp;
<<<<<<< HEAD

=======
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
    public ProjectMessage() {
    }
    public ProjectMessage(String userHead, double donateMoney, String userName, String timestamp) {
        this.userHead = userHead;
        this.donateMoney = donateMoney;
        this.userName = userName;
        this.timestamp = timestamp;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public double getDonateMoney() {
        return donateMoney;
    }

    public void setDonateMoney(double donateMoney) {
        this.donateMoney = donateMoney;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
