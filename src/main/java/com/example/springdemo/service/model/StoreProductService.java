package com.example.springdemo.service.model;

import com.example.springdemo.dao.domain.StoreProduct;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface StoreProductService {

    StoreProduct add(StoreProduct storeProduct) throws NoSuchAlgorithmException;

    void update(StoreProduct storeProduct);

    void productHide(StoreProduct storeProduct);

    void updateStatus(StoreProduct storeProduct);

    List<StoreProduct> selectProduct();
}
