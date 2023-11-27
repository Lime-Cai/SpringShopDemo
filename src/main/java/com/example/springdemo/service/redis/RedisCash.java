package com.example.springdemo.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;


public interface RedisCash {

     void put(String key, String value) ;

     String get(String key) ;

     <T> void putObject(String key, Object value) throws JsonProcessingException;

     <T> T getObject(String key, Class<T> clazz) throws JsonProcessingException;
}
