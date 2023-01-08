package com.example.springdemo.service.impl;

import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.entity.HsUser;
import com.example.springdemo.tools.SystemTools;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springdemo.service.model.HsUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HsUserServiceImpl implements HsUserService {

    @Autowired
    private HsUserRepository hsUserRepository;
    @Autowired
    private HsUserMapper hsUserMapper;
    @Autowired
    private SystemTools systemTools;

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
        if (systemTools.isNullStringTools(hsUser,hsUser.getUsername(),"判斷UserName")){
            return "system/login_error";
        }

        if (systemTools.isNullStringTools(hsUser,hsUser.getPassword(),"判斷PassWord")){
            return "system/login_error";
        }

        // 產生 token 如果重複再產生
        String token = systemTools.uuidToken();
        while (hsUserMapper.findToken(token) > 0){
            token = systemTools.uuidToken();
        }
        hsUser.setToken(token);



        hsUserRepository.save(hsUser);
        return "system/login_success";
    }

    @Override
    public void delete(Integer id) {

    }
}
