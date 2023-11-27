package com.example.springdemo.common.redis;

import com.example.springdemo.service.redis.RedisCashImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class redisConfig {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;

    //@Bean
    public RedisCashImpl redisCash() {
        return new RedisCashImpl(redisTemplate, objectMapper);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(final RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        return redisTemplate;
    }

    //@Bean
    //public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    //    RedisTemplate<String, String> template = new RedisTemplate<>();
    //    template.setConnectionFactory(factory);
    //    template.setKeySerializer(new StringRedisSerializer());
    //    template.setValueSerializer(new StringRedisSerializer());
    //    template.afterPropertiesSet();
    //    return template;
    //}


}
