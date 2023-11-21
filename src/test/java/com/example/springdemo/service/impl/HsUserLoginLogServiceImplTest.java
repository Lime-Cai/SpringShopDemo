package com.example.springdemo.service.impl;

import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.mapper.HsUserLoginLogMapper;
import com.example.springdemo.dao.repository.HsUserLoginLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.*;

class HsUserLoginLogServiceImplTest {
    @Mock
    HsUserLoginLogRepository hsUserLoginLogRepository;
    @Mock
    HsUserLoginLogMapper hsUserLoginLogMapper;
    @Mock
    Logger log;
    @InjectMocks
    HsUserLoginLogServiceImpl hsUserLoginLogServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveLog() {
        when(hsUserLoginLogMapper.findFrequency(anyInt())).thenReturn(null);

        hsUserLoginLogServiceImpl.saveLog(new HsUser(Integer.valueOf(1), "token", "username", "password", "name", "mail", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Boolean.TRUE, LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 39), LocalDateTime.of(2023, Month.NOVEMBER, 21, 0, 12, 39)), Boolean.TRUE);
    }

    @Test
    void testUpdate() {
        hsUserLoginLogServiceImpl.update(Integer.valueOf(0));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: https://weirddev.com/forum#!/testme