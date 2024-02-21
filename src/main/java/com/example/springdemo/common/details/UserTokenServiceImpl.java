package com.example.springdemo.common.details;

import com.example.springdemo.dao.entity.enums.RedisTypeEnum;
import com.example.springdemo.service.redis.RedisCash;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.security.sasl.AuthenticationException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {

    private final RedisCash redisCash;

    @Override
    public TokenUser findTokenUser(String username) {
        TokenUser token = redisCash.getAndRefresh(RedisTypeEnum.TOKEN, username,TokenUser.class,RedisTypeEnum.TOKEN.getTimeOut(), TimeUnit.MILLISECONDS);

        if (!StringUtils.hasText(token.getToken())) {
            try {
                throw new AuthenticationException(HttpStatus.FORBIDDEN.getReasonPhrase());
            } catch (AuthenticationException e) {
                throw new RuntimeException(e);
            }
        }

        return token;
    }
}
