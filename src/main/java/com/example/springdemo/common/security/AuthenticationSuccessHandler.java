package com.example.springdemo.common.security;

import com.example.springdemo.common.details.TokenUser;
import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.entity.enums.RedisTypeEnum;
import com.example.springdemo.dao.repository.HsUserRepository;
import com.example.springdemo.service.redis.RedisCash;
import com.example.springdemo.tools.DateTools;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private final RedisCash redisCash;

    private final HsUserRepository hsUserRepository;

    /**
     * UserDetails loadUserByUsername 登次成功后的逻辑操作
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                .stream()
                .filter(StringUtils::hasText)
                .findFirst()
                .orElseThrow(() -> new BadCredentialsException(HttpStatus.FORBIDDEN.getReasonPhrase()));

        TokenUser userDetails = (TokenUser) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        log.info("Login User  [ {} ] Role  {}  Token [ {} ] Time [ {} ] ", authentication.getName(), userDetails.getAuthority(), userDetails.getToken(), DateTools.dateFormat(now));
        HsUser hsUser = hsUserRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        hsUser.setToken(userDetails.getToken());
        hsUser.setLastLoginTime(now);
        hsUserRepository.saveAndFlush(hsUser);

        redisCash.putObject((RedisTypeEnum.TOKEN +"-"+hsUser.getToken()), userDetails);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("OK");
    }

}
