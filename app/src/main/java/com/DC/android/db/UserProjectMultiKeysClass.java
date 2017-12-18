package com.DC.android.db;

import java.io.Serializable;

/**
 * Created by huangzhenyang on 2017/9/7.
 * UserProject的复合主键类
 *
 * @Param userId
 * @Param projectId
 * @Param timestamp
 * 由这三个共同组成复合主键
 */
public class UserProjectMultiKeysClass implements Serializable {
    private Integer userId;
    private Integer projectId;
    private String timestamp;

    //Constructor
    public UserProjectMultiKeysClass() {
    }

    public UserProjectMultiKeysClass(Integer userId, Integer projectId, String timestamp) {
        this.userId = userId;
        this.projectId = projectId;
        this.timestamp = timestamp;
    }

    //Setter and Getter
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    //重写hashCode与equals方法
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((userId == null) ? 0 : userId.hashCode());
        result = PRIME * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = PRIME * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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

        final UserProjectMultiKeysClass other = (UserProjectMultiKeysClass)obj;
        if(userId == null){
            if(other.userId != null){
                return false;
            }
        }else if(!userId.equals(other.userId)){
            return false;
        }
        if(projectId == null){
            if(other.projectId != null){
                return false;
            }
        }else if(!projectId.equals(other.projectId)){
            return false;
        }
        if(timestamp == null){
            if(other.timestamp != null){
                return false;
            }
        }else if(!timestamp.equals(other.timestamp)){
            return false;
        }

        return true;
    }
}
