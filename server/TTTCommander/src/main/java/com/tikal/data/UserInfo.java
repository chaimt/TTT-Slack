package com.tikal.data;

import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by Haim.Turkel on 2/16/2016.
 */
public class UserInfo {
    private String userId;
    private String project;
    private Date in;
    private Date out;

    public UserInfo(){}

    public UserInfo(String userId, String project, Date in, Date out) {
        this.userId = userId;
        this.project = project;
        this.in = in;
        this.out = out;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getIn() {
        return in;
    }

    public void setIn(Date in) {
        this.in = in;
    }

    public Date getOut() {
        return out;
    }

    public void setOut(Date out) {
        this.out = out;
    }

    public boolean isValid(){
        return !StringUtils.isEmpty(userId);
    }
}
