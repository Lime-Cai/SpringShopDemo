package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserLoginLogMapper;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserLoginLogRepository;
import com.example.springdemo.entity.HsUser;
import com.example.springdemo.entity.HsUserLoginLog;
import com.example.springdemo.service.model.HsUserLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class HsUserLoginLogServiceImpl implements HsUserLoginLogService {

    @Autowired
    private HsUserLoginLogRepository hsUserLoginLogRepository;
    @Autowired
    private HsUserLoginLogMapper hsUserLoginLogMapper;

    @Autowired
    private HsUserMapper hsUserMapper;

    @Override
    public void saveLog(HsUser hsUser ,Boolean check) {
        HsUserLoginLog hsUserLoginLog =new HsUserLoginLog();
        Integer status = null;
        if (check){
            status = 0;
        }else {
            status = 9 ;
        }
        Integer count = 0;
        if (status == 9){
            count = hsUserLoginLogMapper.findFrequency(hsUser.getId());
            count++;
        }
        String remark = "帳號 : "+hsUser.getUsername()+" 登陸失敗 [ "+count+" ] 次";

        if (count >= 5){
            hsUser.setStatus(9);
            hsUserMapper.updateUser(hsUser.getId(),hsUser);
        }

        hsUserLoginLog.builder().
                user_id(hsUser.getId()).
                status(status).
                frequency(count).
                remark(remark).
                login_time(LocalDateTime.now()).
                build();
        hsUserLoginLogRepository.save(hsUserLoginLog);
    }

    @Override
    public Integer getLastLoginFrequency(Integer user_id) {
        return null;
    }

    @Override
    public String checkLoginFrequency(HsUser hsUser) {
        Integer count = hsUserLoginLogMapper.findFrequency(hsUser.getId());
        if (count >= 5){
            return "login_frequency";
        }
        return null;
    }


}
