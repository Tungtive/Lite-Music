package com.DC.android.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by huangzhenyang on 2017/9/7.
 * 项目
 */

public class Project extends DataSupport implements Serializable {

    private Integer id;
    private String projectName; // 项目名称
    private String initiatorName; // 发起方名称
    private String img; // 首页的图片链接
    private String description; // 简短的描述
    private double targetMoney; // 目标筹款金额
    private double currentMoney; // 当前已筹款的金额
    private String detail; // 项目详情
    private int peopleNum;
    private String startTime;
    private  String imgListStr;
    private int pid;

    // 构造器
    public Project() {
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Project(int pid, String projectName, String initiatorName, String img, String description, double targetMoney, double currentMoney, String detail, int peopleNum , String startTime, String imgListStr) {
        this.projectName = projectName;
        this.initiatorName = initiatorName;
        this.img = img;
        this.description = description;
        this.targetMoney = targetMoney;
        this.currentMoney = currentMoney;
        this.pid=pid;

        this.detail = detail;
        this.peopleNum=peopleNum;
        this.startTime=startTime;
        this.imgListStr=imgListStr;

    }

    //getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImgListStr() {
        return imgListStr;
    }

    public void setImgListStr(String imgListStr) {
        this.imgListStr = imgListStr;
    }
    //to string

   /* @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", initiatorName='" + initiatorName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", targetMoney='" + targetMoney + '\'' +
                ", currentMoney='" + currentMoney + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }*/
}
