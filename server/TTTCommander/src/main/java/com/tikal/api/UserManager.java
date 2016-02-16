package com.tikal.api;

import com.tikal.data.UserInfo;

import java.util.Date;

/**
 * Created by Haim.Turkel on 2/16/2016.
 */
public interface UserManager {
    public void saveUser(UserInfo userInfo);
    public void setDefaultProject(String userName, String project);
    public void generateReport(String userName, Date yearMonth);
    public void validateReport(String userName, Date yearMonth);
}
