package com.example.springdemo.common.details;

import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.entity.enums.RolesEnums;
import com.example.springdemo.dao.mapper.HsUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final HsUserMapper hsUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HsUser hsUser = hsUserMapper.selectOneByUsername(username).orElseGet(HsUser::new);
        if (!StringUtils.hasText(hsUser.getUsername())){
            throw new UsernameNotFoundException(HttpStatus.FORBIDDEN.getReasonPhrase());
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        return new TokenUser(hsUser.getUsername(), hsUser.getPassword(), RolesEnums.authoritiesToCollection(hsUser.getRole()), token);
    }
}
