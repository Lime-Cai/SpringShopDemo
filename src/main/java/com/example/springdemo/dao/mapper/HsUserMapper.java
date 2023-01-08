package com.example.springdemo.dao.mapper;

import com.example.springdemo.entity.HsUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HsUserMapper {

    Integer findToken(String token);

    HsUser insertHsUser(HsUser hsUser);
}
