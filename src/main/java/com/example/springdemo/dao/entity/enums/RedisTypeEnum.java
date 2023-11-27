package com.example.springdemo.dao.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RedisTypeEnum {

    TOKEN(300);

    /**
     *  timeOut 为秒
     */
    private final long timeOut;
}
