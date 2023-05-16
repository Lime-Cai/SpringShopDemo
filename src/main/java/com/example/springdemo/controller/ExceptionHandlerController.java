package com.example.springdemo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping
public class ExceptionHandlerController {

    // NOT_FOUND 指到的是404
    // 只要 request = 404 都会执行这个方法
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound() {
        return "admin/error";
    }
}
