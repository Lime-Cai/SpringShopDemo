package com.example.springdemo.service.model;


import com.example.springdemo.dao.entity.HsUser;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HsUserService {
    ResponseEntity<String> register(HsUser user);

    ResponseEntity<String> login(final HttpServletResponse response, String userName, String password);

    void unlock(String password);

    Optional<HsUser> findById(int id);

    Optional<HsUser> findByUsername(String username);

    List<HsUser> findAll();

    void delete(Integer id);

    void update(HsUser user);

    void update(Integer id);
}
