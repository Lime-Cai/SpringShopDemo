package com.example.springdemo.dao.repository;

import com.example.springdemo.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Integer> {
}