package com.example.springdemo;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test/")
public class test {


    @GetMapping("/")
    public String test(){
        String test ="Hello JAVA~~";
        return  test;
    }

}
