package com.example.springdemo.dao.repository;

import com.example.springdemo.dao.domain.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Integer> {
}