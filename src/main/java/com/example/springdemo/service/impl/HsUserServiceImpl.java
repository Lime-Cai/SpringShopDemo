package com.example.springdemo.service.impl;

import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.entity.HsUser;
import com.example.springdemo.tools.SystemTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springdemo.service.model.HsUserService;

import java.util.List;
import java.util.Optional;

@Component
public class HsUserServiceImpl implements HsUserService {

    @Autowired
    private HsUserRepository hsUserRepository;

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
        if (systemTools.isNullStringTools(hsUser,hsUser.getUsername(),"UserName")){
            return "system/error";
        }
        hsUserRepository.save(hsUser);
        return "system/success";
    }

    @Override
    public void delete(Integer id) {

    }
}
