package com.example.springdemo.dao.mapper;

import com.example.springdemo.dao.domain.HsUser;
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

    // 確認帳號密碼是否正確
    Boolean findLoginCheck(String username, String password);

    void updateUser( HsUser hsUser);
}
