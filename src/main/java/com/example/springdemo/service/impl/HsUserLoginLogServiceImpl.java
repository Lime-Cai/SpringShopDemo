package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserLoginLogMapper;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserLoginLogRepository;
import com.example.springdemo.dao.domain.HsUser;
import com.example.springdemo.dao.domain.HsUserLoginLog;
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
        int status = 0;
        Integer count = 0;
        String remark = "";
        // 登錄失敗
        if (!check) {
            count = hsUserLoginLogMapper.findFrequency(hsUser.getId()).orElseGet(() -> 1);
            if (count >1) {
                count++;
                remark = "帳號 : " + hsUser.getUsername() + " 登陸失敗 [ " + count + " ] 次";
                status = 9;
            }
            // 登錄失敗超過次數封鎖
            if (count >= 5) {
                hsUser.setStatus(9);
                hsUserMapper.updateHsUser(hsUser);
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
