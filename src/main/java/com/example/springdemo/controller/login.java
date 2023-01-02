package com.example.springdemo.controller;

import com.example.springdemo.entity.HsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springdemo.service.model.HsUserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class login {

    @Autowired
    private HsUserService hsUserService;


    @GetMapping("/")
    public String login(){
        return "system/login";
    }
    @GetMapping("/test")
    public String test(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/query/{id}")
    public Optional<HsUser> oneUser(@PathVariable("id") Long id){
      return hsUserService.findById(id);
    }


    @GetMapping("/query/all")
    public String allUser(Model model){
        model.addAttribute("alluser",hsUserService.findAll());
        return "admin/query";
    }

    @PostMapping("/add")
    public void save(@ModelAttribute HsUser hsUser){
        hsUserService.save(hsUser);

    }

}
