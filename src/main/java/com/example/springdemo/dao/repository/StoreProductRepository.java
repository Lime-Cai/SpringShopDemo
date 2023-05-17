package com.example.springdemo.dao.repository;

import com.example.springdemo.dao.domain.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Integer> {

    @Query(value = "select s.* from StoreProduct s left join hs_user u on u.id = s.admin_id where s.product_id :productId and u.token = token" ,nativeQuery = true)
    Optional<StoreProduct> findByProductId(String productId,String token);
}