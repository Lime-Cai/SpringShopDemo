package com.example.springdemo.dao.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.springdemo.dao.domain.HsUser;
import org.springframework.stereotype.Repository;

/**
 * @author jp098
 * @description 针对表【hs_user】的数据库操作Mapper
 * @createDate 2023-02-20 19:29:15
 * @Entity com.example.springdemo.dao.domain.HsUser
 */
@Mapper
@Repository
public interface HsUserMapper {
    HsUser selectOneByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    HsUser selectOneByUsername(@Param("username") String username);

    HsUser selectOneByToken(@Param("token") String token);

    List<HsUser> selectAllByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    void updateHsUser(HsUser hsUser);


}




