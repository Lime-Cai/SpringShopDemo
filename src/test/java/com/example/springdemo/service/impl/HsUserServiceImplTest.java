package com.example.springdemo.service.impl;

import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.service.model.HsUserLoginLogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class HsUserServiceImplTest {
    @Mock
    HsUserRepository hsUserRepository;
    @Mock
    HsUserMapper hsUserMapper;
    @Mock
    HsUserLoginLogService hsUserLoginLogService;
    @Mock
    Logger log;
    @InjectMocks
    HsUserServiceImpl hsUserServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        when(hsUserMapper.selectOneByUsername(anyString())).thenReturn(null);

        ResponseEntity<String> result = hsUserServiceImpl.register(new HsUser(Integer.valueOf(0), "token", "username", "password", "name", "mail", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Boolean.TRUE, LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 17), LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 17)));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testLogin() {
        when(hsUserMapper.selectOneByUsernameAndPassword(anyString(), anyString())).thenReturn(null);
        when(hsUserMapper.selectOneByUsername(anyString())).thenReturn(null);

        ResponseEntity<String> result = hsUserServiceImpl.login("userName", "password");
        Assertions.assertEquals(null, result);


        // 创建 UserRepository 的模拟对象
        HsUserMapper userRepository = mock(HsUserMapper.class);

        // 创建 UserService 实例，注入模拟的 UserRepository
        HsUser userService = new HsUser(userRepository);

        // 创建一个模拟的用户对象
        HsUser mockUser = new HsUser("userName", "password");

        // 模拟 UserRepository 的行为，当调用 findByUsername 方法时返回包含模拟用户的 Optional
        when(userRepository.findByUsername("userName")).thenReturn(Optional.of(mockUser));

        // 执行测试
        ResponseEntity<String> result = userService.login("userName", "password");

        // 验证行为，确保 findByUsername 方法被调用了一次
        verify(userRepository, times(1)).findByUsername("userName");

        // 验证结果，确保返回了 OK 状态码
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testUnlock() {
        hsUserServiceImpl.unlock("password");
    }

    @Test
    void testUpdate() {
        hsUserServiceImpl.update(new HsUser(Integer.valueOf(0), "token", "username", "password", "name", "mail", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Boolean.TRUE, LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 17), LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 17)));
    }

    @Test
    void testFindById() {
        Optional<HsUser> result = hsUserServiceImpl.findById(0);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindAll() {
        List<HsUser> result = hsUserServiceImpl.findAll();
        Assertions.assertEquals(List.of(new HsUser(Integer.valueOf(0), "token", "username", "password", "name", "mail", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Boolean.TRUE, LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 17), LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 17))), result);
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