package com.example.springdemo.controller;

import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.service.model.HsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class UserLoginController {

    private final HsUserService hsUserService;

    //@GetMapping("/")
    //public ResponseEntity<String> login(final HttpServletResponse response, final String userName, final String password) {
    //    return hsUserService.login(response, userName, password);
    //}

    @GetMapping("/test")
    public String login() {
        return "test";
    }
    @GetMapping("/test2")
    public String login2() {
        return "test2";
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
