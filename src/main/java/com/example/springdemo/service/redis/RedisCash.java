package com.example.springdemo.service.redis;

import com.example.springdemo.dao.entity.enums.RedisTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.TimeUnit;


public interface RedisCash {

    void putEnumDefaultTimeOut(final RedisTypeEnum typeEnum, final String key, final String value);

    void put(final RedisTypeEnum typeEnum, final String key, final String value, final long timeout, final TimeUnit timeUnit);

    String get(String key);

    String getAndRefresh(final RedisTypeEnum typeEnum, final String key);

    <T> void putObject(String key, Object value) throws JsonProcessingException;

    <T> T getObject(String key, Class<T> clazz) throws JsonProcessingException;
}
