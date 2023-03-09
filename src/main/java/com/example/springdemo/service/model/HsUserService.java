package com.example.springdemo.service.model;


import com.example.springdemo.dao.domain.HsUser;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public interface HsUserService {
    String loginCheck(HsUser user, HttpServletResponse response);
    Optional<HsUser> findById(Long id);
    List<HsUser> findAll();
    String save(HsUser user);
    void delete(Integer id);
    void update(Integer id);
}
