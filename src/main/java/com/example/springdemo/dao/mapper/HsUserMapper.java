package com.example.springdemo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HsUserMapper {
    @Select("select count(1) from hs_user where token=#{token}")
    int findToken(@Param("token") String token);

    Integer findUsername(@Param("username") String username);

    // 再修正
    Integer findPassword(@Param("password") String password);
}
