package com.example.springdemo.dao.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus{
    UNVERIFIED("未验证"),
    COMPLETED("验证完成");
    //封锁(9);

    final String value;
}
