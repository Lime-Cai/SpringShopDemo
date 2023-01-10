package com.example.springdemo.dao.repository;

import com.example.springdemo.entity.HsUserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HsUserLoginLogRepository extends JpaRepository<HsUserLoginLog, Integer> {

}