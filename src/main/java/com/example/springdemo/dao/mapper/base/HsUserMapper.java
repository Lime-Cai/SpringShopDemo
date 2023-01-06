package com.example.springdemo.dao.mapper.base;

import com.example.springdemo.entity.HsUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HsUserMapper {

    int findToken(String token);

    HsUser insertHsUser(HsUser hsUser);
}
