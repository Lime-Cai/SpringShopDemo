package com.example.springdemo.service.model;

import com.example.springdemo.dao.entity.HsUser;

public interface HsUserLoginLogService {
    void saveLog(HsUser hsUser,Boolean check);
    void update(Integer user_id);

}
