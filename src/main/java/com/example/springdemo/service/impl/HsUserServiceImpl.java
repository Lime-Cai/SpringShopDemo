package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.entity.HsUser;
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

    @Override
    public Boolean loginCheck(HsUser user) {
        Integer count = hsUserMapper.findLogigCheck(user.getUsername(), user.getPassword());
        if(count <=0){
            log.error("[ERROR] 登陸失敗 帳號 : [ "+user.getUsername()+" ] 密碼 : [ "+user.getPassword()+" ]");
            return false;
        }
        user.setLast_login_time(LocalDateTime.now());
        log.info("使用者 : [ "+user.getUsername()+" ]" +" 登陸成功 "+LocalDateTime.now());
        return true;
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
        System.out.println(hsUser.getUsername()+"  "+hsUser.getPassword());
        if (systemTools.isNullStringTools(hsUser,hsUser.getUsername(),"UserName")){
            return "system/login_error";
        }

        if (systemTools.isNullStringTools(hsUser,hsUser.getPassword(),"PassWord")){
            return "system/login_error";
        }

        if (hsUserMapper.findUsername(hsUser.getUsername()) >0){
            log.error("[ERROR] 帳號重複 : [ "+hsUser.getUsername()+" ]");
            return "system/login_error";
        }

        // 產生 token 如果重複再產生
        String token = systemTools.uuidToken();
        while (hsUserMapper.findToken(token) > 0){
            token = systemTools.uuidToken();
        }
       HsUser user = hsUser.builder().
                username(hsUser.getUsername()).
                password(hsUser.getPassword()).
                token(token).
                add_time(LocalDateTime.now()).
                last_login_time(LocalDateTime.now()).
                is_admin(0).build();

        log.info("註冊成功 UserName : { " + hsUser.getUsername()+" }");hsUserRepository.save(user);
        return "system/login_success";
    }

    @Override
    public void delete(Integer id) {

    }
}
