package com.example.springdemo.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class mssSystem {

    @RequestMapping("/system")
    public static String system(){

        return "system";
    }
}
