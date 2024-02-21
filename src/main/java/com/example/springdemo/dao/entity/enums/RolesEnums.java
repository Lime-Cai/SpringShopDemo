package com.example.springdemo.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public enum RolesEnums {
    USER("user"),
    ADMIN("admin"),
    SUPER("super"),
    OTHER("other");

    private String roleName;

    private RolesEnums getRole(String role){
        return Arrays.stream(RolesEnums.values())
                .filter(enums -> Objects.equals(enums.roleName,role))
                .findFirst()
                .orElseGet(()-> OTHER);
    }

    public static Collection<? extends GrantedAuthority> authoritiesToCollection(RolesEnums rolesEnums){
        rolesEnums = Optional.ofNullable(rolesEnums).orElseGet(()->OTHER);
        return Collections.singleton(new SimpleGrantedAuthority(rolesEnums.roleName));
    }
}
