package com.example.springdemo.controller;

import com.example.springdemo.entity.HsUser;
import com.example.springdemo.tool.systemTool;
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

    systemTool systemTool =new systemTool();


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
    public List<HsUser> allUser(Model model){
        model.addAttribute("alluser",hsUserService.findAll());
        return "admin/query";
    }

    @PostMapping("/save")
    public void save(@ModelAttribute Model model){
        System.out.println("FFFF");

    }

}
