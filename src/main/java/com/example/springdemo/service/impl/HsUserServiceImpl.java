package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserLoginLogMapper;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.entity.HsUser;
import com.example.springdemo.service.model.HsUserLoginLogService;
import com.example.springdemo.tools.SystemTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springdemo.service.model.HsUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HsUserServiceImpl implements HsUserService {

    @Autowired
    private HsUserRepository hsUserRepository;
    @Autowired
    private HsUserMapper hsUserMapper;
    @Autowired
    private SystemTools systemTools;
    @Autowired
    private HsUserLoginLogService hsUserLoginLogService;

    @Override
    public Integer loginCheck(HsUser hsUser) {
        Boolean check = true;
        // 1 = 帳號密碼符合
//        try {
        Integer count = hsUserMapper.findLogigCheck(hsUser.getUsername(), hsUser.getPassword());

        // 登陸失敗
        if (count == 0) {
            HsUser user = hsUserRepository.findByUsername(hsUser.getUsername()).orElse(null);
            // 沒有此帳號 不儲存
            if (user == null) {
                return 0;
            } else// 有此帳號
            {
                hsUserLoginLogService.saveLog(user, false);
                log.error("[ERROR] 登陸失敗 帳號 : [ " + hsUser.getUsername() + " ] 密碼 : [ " + hsUser.getPassword() + " ]");

                // 有此帳號 登陸失敗超過次數
                if (user.getStatus() == 9) {
                    return 9;
                }
                return 0;
            }
        }
        HsUser user = hsUserRepository.findByUsernameAndPassword(hsUser.getUsername(), hsUser.getPassword());
        if (user.getStatus() == 9) {
            return 9;
        }
        user.setLast_login_time(LocalDateTime.now());
        hsUserMapper.updateUser(user);

        log.info("使用者 : [ " + user.getUsername() + " ]" + " 登陸成功 " + LocalDateTime.now());
        hsUserLoginLogService.saveLog(user, check);

        if (check) {
            System.out.println("1");
            return 1;
        } else {
            System.out.println("0");
            return 0;
        }
//        } catch (Exception e){
//            System.out.println(e+"登陸異常");
//            return 0;
//        }
    }

    @Override
    public Optional<HsUser> findById(Long id) {
        return hsUserRepository.findById(id);
    }

    @Override
    public List<HsUser> findAll() {
        return hsUserRepository.findAll();
    }

    @Override
    public String save(HsUser hsUser) {
        System.out.println(hsUser.getUsername() + "  " + hsUser.getPassword());
        if (systemTools.isNullStringTools(hsUser, hsUser.getUsername(), "UserName")) {
            return "system/login_save_error";
        }

        if (systemTools.isNullStringTools(hsUser, hsUser.getPassword(), "PassWord")) {
            return "system/login_save_error";
        }

        if (hsUserMapper.findUsername(hsUser.getUsername()) > 0) {
            log.error("[ERROR] 帳號重複 : [ " + hsUser.getUsername() + " ]");
            return "system/login_save_error";
        }

        // 產生 token 如果重複再產生
        String token = systemTools.uuidToken();
        while (hsUserMapper.findToken(token) > 0) {
            token = systemTools.uuidToken();
        }
        HsUser user = hsUser.builder().
                username(hsUser.getUsername()).
                password(hsUser.getPassword()).
                token(token).
                status(0).
                add_time(LocalDateTime.now()).
                last_login_time(LocalDateTime.now()).
                is_admin(0).build();

        log.info("註冊成功 UserName : { " + hsUser.getUsername() + " }");
        hsUserRepository.save(user);
        return "system/login_save_success";
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id) {
        HsUser hsUser = hsUserRepository.getById(id);
        hsUser.setStatus(1);
        hsUserMapper.updateUser(hsUser);


    }
}
