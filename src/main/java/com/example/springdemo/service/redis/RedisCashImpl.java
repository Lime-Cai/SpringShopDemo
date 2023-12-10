package com.example.springdemo.service.redis;

import com.example.springdemo.dao.entity.enums.RedisTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCashImpl implements RedisCash {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void putEnumDefaultTimeOut(RedisTypeEnum typeEnum, String key, String value) {
        put(bindCacheKey(typeEnum.toString(), key), value, typeEnum.getTimeOut(), TimeUnit.SECONDS);
    }

    public void put(final String key, final String value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public void put(final RedisTypeEnum typeEnum, final String key, final String value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(bindCacheKey(typeEnum.toString(), key), value, timeout, timeUnit);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getAndRefresh(final String cacheName, final String key, final Class<T> valueType, final long timeout, final TimeUnit unit) {
        final String cacheKey = bindCacheKey(cacheName, key);
        final String result = redisTemplate.opsForValue().get(cacheKey);
        final JavaType javaType = TypeFactory.defaultInstance().constructType(valueType);
        if (result != null) {
            try {
                redisTemplate.expire(cacheKey, timeout, unit);
                return objectMapper.readValue(result, javaType);
            } catch (final IOException e) {
                log.error("parser result error:{}", result);
                log.error("", e);
            }
        }
        return null;
    }

    @Override
    public <T> void putObjectEnumDefaultTimeOut(final RedisTypeEnum typeEnum,final String key,final T value) {
        final String cacheKey = bindCacheKey(typeEnum.toString(), key);
        try {
            redisTemplate.opsForValue().set(cacheKey, new String(objectMapper.writeValueAsBytes(value), StandardCharsets.UTF_8), typeEnum.getTimeOut(), TimeUnit.SECONDS);
        } catch (final JsonProcessingException e) {
            log.error("write value error:{}", value);
        }
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) throws JsonProcessingException {
        String jsonValue = redisTemplate.opsForValue().get(key);
        return jsonValue != null ? objectMapper.readValue(jsonValue, clazz) : null;
    }

    private String bindCacheKey(String typeEnum, String key) {
        return String.format("%s-%s", typeEnum, key);
    }
}

