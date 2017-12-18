package com.DC.android.db;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

/**
 * Created by huangzhenyang on 2017/9/7.
 */
//@Entity
public class Organization {
   // @Id
   // @GeneratedValue
    private Integer id;

    private String organizationName; //组织名称
    private String manager; //管理人员
    private String managerImg; //管理人员头像

    // 构造器
    public Organization() {
    }
    public Organization(String organizationName, String manager, String managerImg) {
        this.organizationName = organizationName;
        this.manager = manager;
        this.managerImg = managerImg;
    }

    //getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerImg() {
        return managerImg;
    }

    public void setManagerImg(String managerImg) {
        this.managerImg = managerImg;
    }

    //to string
    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", organizationName='" + organizationName + '\'' +
                ", manager='" + manager + '\'' +
                ", managerImg='" + managerImg + '\'' +
                '}';
    }
}
