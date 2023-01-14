package com.example.springdemo.service.model;


import com.example.springdemo.entity.HsUser;

import java.util.List;
import java.util.Optional;

public interface HsUserService {
    Integer loginCheck(HsUser user);
    Optional<HsUser> findById(Long id);
    List<HsUser> findAll();
    String save(HsUser user);
    void delete(Integer id);
    void update(Integer id);
}
