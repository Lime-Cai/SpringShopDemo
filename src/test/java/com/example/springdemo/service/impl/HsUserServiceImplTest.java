package com.example.springdemo.service.impl;

import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.service.model.HsUserLoginLogService;
import com.example.springdemo.service.redis.RedisCashImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class HsUserServiceImplTest {
    @InjectMocks
    HsUserServiceImpl hsUserServiceImpl;

    @Mock
    RedisCashImpl redisCash;
    @Mock
    HsUserRepository hsUserRepository;
    @Mock
    HsUserMapper hsUserMapper;
    @Mock
    HsUserLoginLogService hsUserLoginLogService;
    @Mock
    Logger log;
    @Mock
    HttpServletResponse mockResponse;

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
    void testRegister() {
        when(hsUserMapper.selectOneByUsername(anyString())).thenReturn(Optional.ofNullable(hsUser));

        ResponseEntity<String> result = hsUserServiceImpl.register(hsUser);
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), result.getBody());
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getBody());
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), result.getBody());
    }

    @Test
    void testLogin() {
        when(hsUserMapper.selectOneByUsernameAndPassword(hsUser.getUsername(), hsUser.getPassword())).thenReturn(Optional.of(hsUser));
        when(hsUserMapper.selectOneByUsername(anyString())).thenReturn(Optional.of(hsUser));

        // 登陆成功
        ResponseEntity<String> successResult = hsUserServiceImpl.login(mockResponse, "userName", "password");
        Assertions.assertEquals(HttpStatus.OK, successResult.getStatusCode());

        //hsUser.setStatus(9);
        //when(hsUserMapper.selectOneByUsername(anyString())).thenReturn(Optional.of(hsUser));
        // 登陆失败
        ResponseEntity<String> failResult = hsUserServiceImpl.login(mockResponse, "userName", "fail");
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, failResult.getStatusCode());

        // 没有此账号
        ResponseEntity<String> noneUserNameResult = hsUserServiceImpl.login(mockResponse, "fail", "password");
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, noneUserNameResult.getStatusCode());
    }

    @Test
    void testUnlock() {
        hsUserServiceImpl.unlock("password");
    }

    @Test
    void testUpdate() {
        hsUserServiceImpl.update(hsUser);
    }

    @Test
    void testFindById() {
        Optional<HsUser> result = hsUserServiceImpl.findById(0);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindAll() {
        List<HsUser> result = hsUserServiceImpl.findAll();
        List<HsUser> list = new ArrayList<>();
        list.add(hsUser);
        Assertions.assertEquals(list, result);
    }

    @Test
    void testDelete() {
        hsUserServiceImpl.delete(Integer.valueOf(0));
    }

    @Test
    void testUpdate2() {
        hsUserServiceImpl.update(Integer.valueOf(0));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: https://weirddev.com/forum#!/testme