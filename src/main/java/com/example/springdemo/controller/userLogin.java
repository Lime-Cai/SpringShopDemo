package com.example.springdemo.controller;

import com.example.springdemo.entity.HsUser;
import com.example.springdemo.service.model.HsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Slf4j
public class userLogin {

    @Autowired
    private HsUserService hsUserService;

    @GetMapping("/")
    public String index() {
        return "system/login";
    }


    @GetMapping("/add")
    public String add(@ModelAttribute HsUser hsUser) {
        return "system/add";
    }
    @GetMapping("/save")
    public String save(@ModelAttribute HsUser hsUser) {
        return hsUserService.save(hsUser);
    }


}
