package com.example.springdemo.dao.repository;

import com.example.springdemo.entity.HsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface HsUserRepository extends JpaRepository<HsUser, Integer> {
    Optional<HsUser> findById(Long id);

}