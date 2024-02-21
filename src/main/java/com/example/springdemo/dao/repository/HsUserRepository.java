package com.example.springdemo.dao.repository;

import com.example.springdemo.dao.entity.HsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HsUserRepository extends JpaRepository<HsUser, Integer>{

    Optional<HsUser> findByUsername(String username);
}