package com.example.springdemo.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum UserStatus{
    未验证(0),
    验证完成(1);
    //封锁(9);

    int value;
}
