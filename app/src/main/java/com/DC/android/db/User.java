package com.DC.android.db;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by huangzhenyang on 2017/8/4.
 */
//@Entity
public class User  extends DataSupport implements Serializable {
   // @Id
   // @GeneratedValue
    private int id;
    private Integer uid;
    private String name; //用户名
    private String password; //密码
    private String email; //邮箱
    private String region; //地址
    private String gender; //性别
    private double balance; //余额
    private String head;
    private double value;
    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public User( String name, String password, String email, String region, String gender, double balance,double value, String head,int uid) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.region = region;
        this.gender = gender;
        this.balance = balance;//爱心值
        this.head=head;//头像
        this.uid=uid;
        this.value=value;

    }

    //getter and setter

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", balance=" + balance +
                '}';
    }
}
