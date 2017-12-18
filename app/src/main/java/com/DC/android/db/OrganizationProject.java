package com.DC.android.db;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.IdClass;

/**
 * Created by huangzhenyang on 2017/9/7.
 */
//@Entity
//@IdClass(OrganizationProjectMultiKeysClass.class)
public class OrganizationProject {
    private String timestamp;
    private Integer organizationId;
    private Integer projectId;

   // @Id
    public Integer getOrganizationId(){
        return this.organizationId;
    }

   // @Id
    public Integer getProjectId(){
        return this.projectId;
    }

    //Getter and Setter
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
