package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.service.model.HsUserLoginLogService;
import com.example.springdemo.tools.SystemTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.springdemo.service.model.HsUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HsUserServiceImpl implements HsUserService {

    private final HsUserRepository hsUserRepository;
    private final HsUserMapper hsUserMapper;
    private final HsUserLoginLogService hsUserLoginLogService;

    @Override
    public ResponseEntity<String> register(HsUser hsUser) {

        if (StringUtils.hasText(hsUser.getUsername()) || StringUtils.hasText(hsUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        }

        if (hsUserMapper.selectOneByUsername(hsUser.getUsername()).isEmpty()) {
            log.error("[ERROR] 帳號重複 : [ {} ]", hsUser.getUsername());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        }

        // TODO token要再思考
        // 產生 token 如果重複再產生
        //String token = SystemTools.uuidToken();
        //while (hsUserMapper.selectOneByToken(token) != null) {
        //    token = SystemTools.uuidToken();
        //}
        HsUser user = hsUser.builder().
                username(hsUser.getUsername()).
                password(hsUser.getPassword()).
                //token(token).
                        status(0).
                addTime(LocalDateTime.now()).
                lastLoginTime(LocalDateTime.now()).
                isStore(0).build();

        log.info("註冊成功 UserName : {}", hsUser.getUsername());
        hsUserRepository.save(user);
        return ResponseEntity.ok("Success");
    }

    @Override
    public ResponseEntity<String> login(String userName, String password) {

        Optional<HsUser> hsUserByUserName = hsUserMapper.selectOneByUsername(userName);
        // 没有这个账号
        if (hsUserByUserName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }

        Optional<HsUser> user = hsUserMapper.selectOneByUsernameAndPassword(userName, password);
        // 有账号但密码不符合
        if (user.isEmpty()) {
            HsUser failUser = hsUserByUserName.get();
            hsUserLoginLogService.saveLog(failUser, false);
            log.error("[ERROR] 登陸失敗 帳號 : [ {} ] ", failUser.getUsername());
            // 登陸失敗超過次數
            if (failUser.getStatus() == 9) {
                log.error("[ERROR] 登陸失敗 已被封鎖 帳號 : [ {} ]", failUser.getUsername());
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        HsUser resultUser = user.get();
        // 登陸成功 更改用户token、更新登录时间
        resultUser.setLastLoginTime(LocalDateTime.now());
        resultUser.setToken(SystemTools.uuidToken());
        hsUserMapper.updateHsUser(resultUser);

        if (resultUser.getStatus() == 9) {
            hsUserLoginLogService.saveLog(resultUser, true);
            log.error("[ERROR] 登陸成功 已被封鎖 帳號 : [ " + resultUser.getUsername() + " ]");
        }
        log.info("使用者 : [ {} ] 登陸成功 登陸時間 : [ {} ]", resultUser.getUsername(), LocalDateTime.now(ZoneId.systemDefault()));
        hsUserLoginLogService.saveLog(resultUser, true);

        // TODO 改成使用Security来获取凭证
        //登陸成功獲取 cookie token
        //response.addCookie(SystemTools.setCookie(user));

        return ResponseEntity.ok("Success");
    }

    @Override
    public void unlock(String password) {

    }

    @Override
    public void update(HsUser user) {
        hsUserMapper.updateHsUser(user);
    }


    @Override
    public Optional<HsUser> findById(int id) {
        return hsUserRepository.findById(id);
    }

    @Override
    public List<HsUser> findAll() {
        return hsUserRepository.findAll();
    }


    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id) {
        HsUser hsUser = hsUserRepository.findById(id).get();
        hsUser.setStatus(1);
        hsUserMapper.updateHsUser(hsUser);
        hsUserLoginLogService.update(id);
        log.info("帳號 : {} 已解鎖", hsUser.getName());
    }
}
