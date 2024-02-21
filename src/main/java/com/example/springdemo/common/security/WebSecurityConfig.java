package com.example.springdemo.common.security;

import com.example.springdemo.common.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final AuthenticationFailureHandler customAuthenticationFailureHandler;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/login/test").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .failureHandler(customAuthenticationFailureHandler)
                .successHandler(authenticationSuccessHandler)
                .and()
                //.exceptionHandling(e -> {
                    //　e.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()));
                    // e.accessDeniedHandler(((request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage())));
                //})
                .logout()
                .and().csrf().disable()
                .cors()
                .and().httpBasic().disable()
        ;
        return http.build();
    }
}
