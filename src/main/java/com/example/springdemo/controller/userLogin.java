package com.example.springdemo.controller;

import com.example.springdemo.entity.HsUser;
import com.example.springdemo.service.model.HsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String login(Model model) {
        model.addAttribute("hsUser",new HsUser());
        model.addAttribute("_method","POST");
        return "system/login";
    }


    @GetMapping("/add")
    public String add(@ModelAttribute HsUser hsUser , Model model) {
        model.addAttribute("_method","POST");
        model.addAttribute("hsUser",new HsUser());
        return "system/login_add";
    }
    @PostMapping("/save")
    public String save( @ModelAttribute HsUser hsUser ,Model model) {
        model.addAttribute("_method","POST");
        model.addAttribute("hsUser",new HsUser());
        System.out.println("SAVE");
        return hsUserService.save(hsUser);
    }


}
