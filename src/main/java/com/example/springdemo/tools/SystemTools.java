package com.example.springdemo.tools;

import com.example.springdemo.entity.HsUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemTools {

    public Boolean isNullStringTools(HsUser hsUser,String s,String remark){
        if (s.trim() == "" && s.equals("") && s == null){
            log.error("[ERROR] USER : {} 命中isNull : {}",hsUser.getUsername(),remark);
        };
        return false;
    }
}
