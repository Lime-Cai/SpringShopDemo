package com.example.springdemo.service.model;

import com.example.springdemo.entity.HsUser;
import com.example.springdemo.entity.HsUserLoginLog;

public interface HsUserLoginLogService {
    void saveLog(HsUser hsUser,Boolean check);

    Integer getLastLoginFrequency(Integer user_id);

    String checkLoginFrequency(HsUser hsUser);
}
