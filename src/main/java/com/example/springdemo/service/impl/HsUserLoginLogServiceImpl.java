package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserLoginLogMapper;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserLoginLogRepository;
import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.entity.HsUserLoginLog;
import com.example.springdemo.service.model.HsUserLoginLogService;
import com.example.springdemo.service.model.HsUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class HsUserLoginLogServiceImpl implements HsUserLoginLogService {

    private final HsUserLoginLogRepository hsUserLoginLogRepository;
    private final HsUserLoginLogMapper hsUserLoginLogMapper;
    //private final HsUserService hsUserService;

    @Override
    public void saveLog(HsUser hsUser, Boolean check) {
        int status = 0;
        int count;
        String remark = "";
        // 登錄失敗
        if (!check) {
            count = hsUserLoginLogMapper.findFrequency(hsUser.getId()).orElse(1);
            if (count > 1) {
                count++;
                remark = String.format("帳號 : %s 登陸失敗 [ %d ] 次", hsUser.getUsername(), count);
                status = 9;
            }
            // 登錄失敗超過次數封鎖
            if (count >= 5) {
                hsUser.setStatus(9);
            }
        } else {
            count = 0;
            remark = String.format("帳號 : %s 登陸成功", hsUser.getUsername());
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
        HsUserLoginLog hsUserLoginLog = HsUserLoginLog
                .builder()
                .user_id(user_id)
                .frequency(0)
                .remark("登陸失敗封鎖 用戶已重新驗證")
                .login_time(LocalDateTime.now())
                .build();
        hsUserLoginLogRepository.save(hsUserLoginLog);
    }
}
