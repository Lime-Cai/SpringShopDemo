package com.example.springdemo.controller;

import com.example.springdemo.entity.HsUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class index {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("hsUser",new HsUser());

        return "index";
    }
}
