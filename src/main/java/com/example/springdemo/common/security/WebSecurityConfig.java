package com.example.springdemo.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    //@Bean
    public UserDetailsService userDetailsService() throws UsernameNotFoundException {
        // ... 实现用户详细信息服务
        // 在这里，你需要根据用户名从数据库或其他地方加载用户信息
        // 以下是一个简化的示例，实际情况应从数据库获取
        //if ("admin".equals(username)) {
        //    return User.builder()
        //            .username("admin")
        //            .password(passwordEncoder().encode("admin"))
        //            .roles("ADMIN")
        //            .build();
        //} else {
        //    throw new UsernameNotFoundException("User not found.");
        //}
        return null;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/login/", "/api/login/register").permitAll() // 允许所有人访问登录和注册
                                .anyRequest().authenticated() // 其他所有请求需要认证
                )
                .formLogin() // 启用默认登录页面
                .and()
                .logout() // 启用默认注销功能
                .and()
                .csrf().disable()
                .httpBasic().disable(); // 启用HTTP Basic认证

        return http.build();
    }
}
