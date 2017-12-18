package com.DC.android.db;

import java.io.Serializable;

/**
 * Created by huangzhenyang on 2017/9/7.
 * OrganizationProject的复合主键类
 *
 * @Param organizationId
 * @Param projectId
 * 由这两个一起组成复合主键
 */
public class OrganizationProjectMultiKeysClass implements Serializable{
    private Integer organizationId;
    private Integer projectId;

    //Constructor
    public OrganizationProjectMultiKeysClass() {
    }
    public OrganizationProjectMultiKeysClass(Integer organizationId, Integer projectId) {
        this.organizationId = organizationId;
        this.projectId = projectId;
    }
    //Getter and Setter
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    //重写hashCode与equals方法
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((organizationId == null) ? 0 : organizationId.hashCode());
        result = PRIME * result + ((projectId == null) ? 0 : projectId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final OrganizationProjectMultiKeysClass other = (OrganizationProjectMultiKeysClass)obj;
        if(organizationId == null){
            if(other.organizationId != null){
                return false;
            }
        }else if(!organizationId.equals(other.organizationId)){
            return false;
        }
        if(projectId == null){
            if(other.projectId != null){
                return false;
            }
        }else if(!projectId.equals(other.projectId)){
            return false;
        }

        return true;
    }
}
