package com.example.springdemo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HsUserLoginLogMapper {
    Integer findFrequency(Integer user_id);
}
