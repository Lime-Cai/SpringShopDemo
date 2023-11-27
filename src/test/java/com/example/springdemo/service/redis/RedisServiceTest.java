package com.example.springdemo.service.redis;

import com.example.springdemo.dao.entity.HsUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

//@SpringBootTest
class RedisServiceTest {
    //@Autowired
    //private RedisCashImpl redisService;
    //
    //@Test
    //void testPutAndGetFromRedis() {
    //    // 调用 RedisTemplate 执行实际的 Redis 操作
    //    redisService.put("key", "value");
    //
    //    // 从 Redis 中获取数据
    //    String result = redisService.get("key");
    //    System.err.println(result);
    //
    //    // 进行断言
    //    Assertions.assertEquals("value", result);
    //}


    @Mock
    private RedisTemplate<String, String> redisTemplate;
    @Mock
    private ValueOperations<String, String> valueOperations;
    @Mock
    ObjectMapper objectMapper;
    @InjectMocks
    RedisCashImpl redisService;

    private HsUser hsUser;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        hsUser = new HsUser();
        hsUser.setId(1);
        hsUser.setUsername("userName");
        hsUser.setPassword("password");
        hsUser.setMail("test@demo.com");
        hsUser.setToken("demodemo");
        hsUser.setName("test");
        hsUser.setStatus(0);
        hsUser.setAddTime(LocalDateTime.now());
        hsUser.setLastLoginTime(LocalDateTime.now());
        hsUser.setIsLock(false);
        hsUser.setIsStore(1);
    }

    @Test
    void testPut() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get("key")).thenReturn("value");

        redisService.put("key", "value", 60, TimeUnit.SECONDS);
        verify(valueOperations).set("key", "value");

        String result = redisService.get("key");
        Assertions.assertEquals("value", result);
        verify(valueOperations).get("key");

    }

    @Test
    void testGet() {
        String result = redisService.get("key");
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testPutObject() throws IOException {
        redisService.putObject("key", hsUser);
    }

    @Test
    void testGetObject() throws IOException {
        HsUser result = redisService.getObject("key", HsUser.class);
        Assertions.assertEquals(new HsUser(), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: https://weirddev.com/forum#!/testme