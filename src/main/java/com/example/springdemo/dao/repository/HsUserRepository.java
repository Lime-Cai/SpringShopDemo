package com.example.springdemo.dao.repository;

import com.example.springdemo.dao.entity.HsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface HsUserRepository extends JpaRepository<HsUser, Integer>{


}