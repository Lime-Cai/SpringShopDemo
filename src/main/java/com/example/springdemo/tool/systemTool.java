package com.example.springdemo.tool;

import org.springframework.beans.factory.annotation.Value;

public class systemTool {

    @Value("${admin.host}")
    public  String host;

}
