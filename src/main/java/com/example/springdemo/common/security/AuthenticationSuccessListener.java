package com.example.springdemo.common.security;

import com.example.springdemo.dao.repository.HsUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Deprecated
public class AuthenticationSuccessListener
        //implements ApplicationListener<AuthenticationSuccessEvent>
{

    private final HsUserRepository hsUserRepository;

    /**
     * UserDetails loadUserByUsername 登次成功后的逻辑操作
     */
    //@Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        //Authentication authentication = event.getAuthentication();
        //TokenUser userDetails = (TokenUser) authentication.getPrincipal();
        //LocalDateTime now = LocalDateTime.now();
        //log.info("Login User  [ {} ] Role  {}  Token [ {} ] Time [ {} ] ", authentication.getName(), userDetails.getAuthority(), userDetails.getToken(), DateTools.dateFormat(now));
        //HsUser hsUser = hsUserRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //
        //hsUser.setToken(userDetails.getToken());
        //hsUser.setLastLoginTime(now);
        //hsUserRepository.saveAndFlush(hsUser);
    }
}
