package com.example.springdemo.controller;

import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.service.model.HsUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class UserLoginController {

    private final HsUserService hsUserService;

    @GetMapping("/")
    public ResponseEntity<String> login(final HttpServletResponse response, final String userName, final String password) {
        return hsUserService.login(response, userName, password);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody HsUser hsUser) {
        return hsUserService.register(hsUser);
    }

    @PutMapping("/unlock")
    public void Unlock(String userName) {
        hsUserService.unlock(userName);
    }
}
