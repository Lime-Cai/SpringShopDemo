package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.dao.domain.HsUser;
import com.example.springdemo.service.model.HsUserLoginLogService;
import com.example.springdemo.tools.SystemTools;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.example.springdemo.service.model.HsUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HsUserServiceImpl implements HsUserService {

    private final HsUserRepository hsUserRepository;
    private final HsUserMapper hsUserMapper;
    private final HsUserLoginLogService hsUserLoginLogService;

    public HsUserServiceImpl(HsUserLoginLogService hsUserLoginLogService, HsUserRepository hsUserRepository, HsUserMapper hsUserMapper, SystemTools systemTools) {
        this.hsUserLoginLogService = hsUserLoginLogService;
        this.hsUserRepository = hsUserRepository;
        this.hsUserMapper = hsUserMapper;
    }

    @Override
    public String loginCheck(HsUser hsUser, HttpServletResponse response) {
        try {
            HsUser user = hsUserMapper.selectOneByUsernameAndPassword(hsUser.getUsername(), hsUser.getPassword());
            // 登陸失敗
            if (user == null) {
                HsUser failUser = hsUserMapper.selectOneByUsername(hsUser.getUsername());
                // 沒有此帳號 不儲存
                if (failUser == null) {
                    return "system/login/login_error";
                } else { // 有此帳號 進行紀錄
                    hsUserLoginLogService.saveLog(failUser, false);
                    log.error("[ERROR] 登陸失敗 帳號 : [ " + hsUser.getUsername() + " ] 密碼 : [ " + hsUser.getPassword() + " ]");
                    // 登陸失敗超過次數
                    if (failUser.getStatus() == 9) {
                        log.error("[ERROR] 登陸失敗 已被封鎖 帳號 : [ " + hsUser.getUsername() + " ]");
                        return "system/login/login_frequency";
                    }
                }
            }
            // 登陸成功 更改用户token、更新登录时间
            user.setLastLoginTime(LocalDateTime.now());
            user.setToken(SystemTools.uuidToken());
            hsUserMapper.updateHsUser(user);

            if (user.getStatus() == 9) {
                hsUserLoginLogService.saveLog(user, true);
                log.error("[ERROR] 登陸成功 已被封鎖 帳號 : [ " + hsUser.getUsername() + " ]");
                return "system/login/login_frequency";
            }
            log.info("使用者 : [ " + user.getUsername() + " ]" + " 登陸成功 " + LocalDateTime.now());
            hsUserLoginLogService.saveLog(user, true);

            //登陸成功獲取 cookie token
            response.addCookie(SystemTools.setCookie(user));
            return "system/login/login_success";

        } catch (Exception e) {
            System.out.println(e + "登陸異常");
            return "system/login/login_error";
        }
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
        if (SystemTools.isNullStringTools(hsUser, hsUser.getUsername(), "UserName")) {
            return "system/login/login_save_error";
        }

        if (SystemTools.isNullStringTools(hsUser, hsUser.getPassword(), "PassWord")) {
            return "system/login/login_save_error";
        }

        if (hsUserMapper.selectOneByUsername(hsUser.getUsername()) != null) {
            log.error("[ERROR] 帳號重複 : [ " + hsUser.getUsername() + " ]");
            return "system/login/login_save_error";
        }

        // 產生 token 如果重複再產生
        String token = SystemTools.uuidToken();
        while (hsUserMapper.selectOneByToken(token) != null) {
            token = SystemTools.uuidToken();
        }
        HsUser user = hsUser.builder().
                username(hsUser.getUsername()).
                password(hsUser.getPassword()).
                token(token).
                status(0).
                addTime(LocalDateTime.now()).
                lastLoginTime(LocalDateTime.now()).
                isStore(0).build();

        log.info("註冊成功 UserName : { " + hsUser.getUsername() + " }");
        hsUserRepository.save(user);
        return "system/login/login_save_success";
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id) {
        HsUser hsUser = hsUserRepository.getById(id);
        hsUser.setStatus(1);
        hsUserMapper.updateHsUser(hsUser);
    }
}
