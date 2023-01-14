package com.example.springdemo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface HsUserLoginLogMapper {
    Optional<Integer> findFrequency(Integer user_id);
}
