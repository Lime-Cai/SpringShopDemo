package com.example.springdemo.controller;

import com.example.springdemo.dao.domain.HsUser;
import com.example.springdemo.service.model.HsUserLoginLogService;
import com.example.springdemo.service.model.HsUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@Slf4j
public class userLogin {

    @Autowired
    private HsUserService hsUserService;
    @Autowired
    private HsUserLoginLogService hsUserLoginLogService;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("_method","POST");
        return "system/login/login";
    }

    @PostMapping("/")
    public String check(@ModelAttribute HsUser hsUser , HttpServletResponse response) {
        return hsUserService.loginCheck(hsUser,response);
    }


    @GetMapping("/add")
    public String add(@ModelAttribute HsUser hsUser , Model model) {
        model.addAttribute("_method","POST");
        model.addAttribute("hsUser",new HsUser());
        return "system/login/login_add";
    }
    @PostMapping("/save")
    public String save( @ModelAttribute HsUser hsUser) {
        return hsUserService.save(hsUser);
    }

    @GetMapping("/status/{id}")
    public String update(@PathVariable("id") Integer id,Model model){
        model.addAttribute("_method","POST");
        hsUserService.update(id);
        hsUserLoginLogService.update(id);
        log.info("帳號 : "+id+"重新認證 已解鎖");
        return  "system/login/login";
    }
}
