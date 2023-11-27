package com.example.springdemo.service.redis;

import com.example.springdemo.dao.entity.enums.RedisTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisCashImpl implements RedisCash {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void putEnumDefaultTimeOut(RedisTypeEnum typeEnum, String key, String value) {
        put(bindCacheKey(typeEnum, key), value, typeEnum.getTimeOut(), TimeUnit.SECONDS);
    }

    @Override
    public void put(final RedisTypeEnum typeEnum, final String key, final String value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(bindCacheKey(typeEnum, key), value, timeout, timeUnit);
    }

    public void put(final String key, final String value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String getAndRefresh(final RedisTypeEnum typeEnum, final String keyValue) {
        final String key = bindCacheKey(typeEnum, keyValue);
        final String result = redisTemplate.opsForValue().get(key);

        if (StringUtils.hasText(result)){
            put(key, result, typeEnum.getTimeOut(), TimeUnit.SECONDS);
        }

        return result;
    }

    @Override
    public <T> void putObject(String key, Object value) throws JsonProcessingException {
        String jsonValue = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, jsonValue);
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) throws JsonProcessingException {
        String jsonValue = redisTemplate.opsForValue().get(key);
        return jsonValue != null ? objectMapper.readValue(jsonValue, clazz) : null;
    }

    private String bindCacheKey(RedisTypeEnum typeEnum, String key) {
        return String.format("%s-%s", typeEnum.toString(), key);
    }
}

