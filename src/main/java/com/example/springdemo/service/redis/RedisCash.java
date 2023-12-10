package com.example.springdemo.service.redis;

import com.example.springdemo.dao.entity.enums.RedisTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.TimeUnit;


public interface RedisCash {

    void putEnumDefaultTimeOut(final RedisTypeEnum typeEnum, final String key, final String value);

    void put(final RedisTypeEnum typeEnum, final String key, final String value, final long timeout, final TimeUnit timeUnit);

    String get(String key);

    <T> T getAndRefresh(final String cacheName, final String key, final Class<T> valueType, final long timeout, final TimeUnit unit);

    <T> void putObjectEnumDefaultTimeOut(RedisTypeEnum typeEnum, String key, T value) throws JsonProcessingException;

    <T> T getObject(String key, Class<T> clazz) throws JsonProcessingException;
}
