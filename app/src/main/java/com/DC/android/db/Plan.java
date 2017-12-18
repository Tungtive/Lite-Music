package com.DC.android.db;


import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by huangzhenyang on 2017/10/19.
 * 用户制定的习惯
 */

public class Plan extends DataSupport implements Serializable {

    private Integer id;
    private Integer plamId;
    private String planName; // 习惯的名字
    private String finishedTimes; // 已经打卡的次数
    private String value; // 爱心值
    private String startDate; // 开始的那天的日期
    private String deadline; // 结束的那天的日期
    private String lastClock; // 最后一次打卡的日期，格式为2017-10-26
    private Boolean isStillKeeping; // 习惯是否还在坚持的布尔变量，判断该次签到的日期与最后一次打卡的日期是否差了一天，
                                    // 是的话为true并且finishedTimes+1,否则为false

    public Plan() {
    }

    public Integer getPlamId() {
        return plamId;
    }

    public void setPlamId(Integer plamId) {
        this.plamId = plamId;
    }

    public Plan(int planId, String planName, String finishedTimes, String value, String startDate, String deadline, String lastClock, Boolean isStillKeeping) {
        this.planName = planName;
        this.finishedTimes = finishedTimes;
        this.value = value;
        this.startDate = startDate;
        this.deadline = deadline;
        this.lastClock = lastClock;
        this.isStillKeeping = isStillKeeping;

        this.plamId=planId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getFinishedTimes() {
        return finishedTimes;
    }

    public void setFinishedTimes(String finishedTimes) {
        this.finishedTimes = finishedTimes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLastClock() {
        return lastClock;
    }

    public void setLastClock(String lastClock) {
        this.lastClock = lastClock;
    }

    public Boolean getStillKeeping() {
        return isStillKeeping;
    }

    public void setStillKeeping(Boolean stillKeeping) {
        isStillKeeping = stillKeeping;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", planName='" + planName + '\'' +
                ", finishedTimes='" + finishedTimes + '\'' +
                ", value='" + value + '\'' +
                ", startDate='" + startDate + '\'' +
                ", deadline='" + deadline + '\'' +
                ", lastClock='" + lastClock + '\'' +
                ", isStillKeeping=" + isStillKeeping +
                '}';
    }
}