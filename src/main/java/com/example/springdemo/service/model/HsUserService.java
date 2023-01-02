package com.example.springdemo.service.model;


import com.example.springdemo.entity.HsUser;

import java.util.List;
import java.util.Optional;

public interface HsUserService {
    Optional<HsUser> findById(Long id);
    List<HsUser> findAll();
    HsUser save(HsUser user);
    void delete(Integer id);
}
