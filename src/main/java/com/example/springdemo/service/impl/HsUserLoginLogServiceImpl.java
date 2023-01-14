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
    public void saveLog(HsUser hsUser, Boolean check) {
        Integer status = null;
        if (check) {
            status = 0;
        } else {
            status = 9;
        }
        Integer count = 0;
        String remark = "";
        // 登錄失敗
        if (status == 9) {
            count = hsUserLoginLogMapper.findFrequency(hsUser.getId()).orElse(null);
            if (count == null) {
                count = 1;
            } else
                count++;
            remark = "帳號 : " + hsUser.getUsername() + " 登陸失敗 [ " + count + " ] 次";

            // 登錄失敗超過次數封鎖
            if (count >= 5) {
                hsUser.setStatus(9);
                hsUserMapper.updateUser(hsUser);
            }
        } else {
            count = 0;
            remark = "帳號 : " + hsUser.getUsername() + " 登陸成功";
        }

        HsUserLoginLog hsUserLoginLog = HsUserLoginLog.builder().
                user_id(hsUser.getId()).
                status(status).
                frequency(count).
                remark(remark).
                login_time(LocalDateTime.now()).
                build();
        hsUserLoginLogRepository.save(hsUserLoginLog);
    }

    @Override
    public void update(Integer user_id) {
        HsUserLoginLog hsUserLoginLog = HsUserLoginLog.builder().user_id(user_id).
                frequency(0).
                remark("登陸失敗封鎖 用戶已重新驗證").
                login_time(LocalDateTime.now()).build();
        hsUserLoginLogRepository.save(hsUserLoginLog);
    }


}
