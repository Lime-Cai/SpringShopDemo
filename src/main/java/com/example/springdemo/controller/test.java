package com.example.springdemo.controller;

import com.example.springdemo.entity.HsUser;
import com.example.springdemo.service.model.HsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@Slf4j
public class test {
    @Autowired
    private HsUserService hsUserService;

    @GetMapping("/")
    public String index(@ModelAttribute HsUser hsUser , Model model) {
        model.addAttribute("_method","POST");
        model.addAttribute("hsUser",new HsUser());
        return "test";
    }
}
