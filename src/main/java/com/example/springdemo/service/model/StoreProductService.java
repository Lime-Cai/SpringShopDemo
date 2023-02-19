package com.example.springdemo.service.model;

import com.example.springdemo.entity.StoreProduct;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface StoreProductService {

    StoreProduct add(StoreProduct storeProduct);

    void update(StoreProduct storeProduct);

    void productHide(StoreProduct storeProduct);

    void updateStatus(StoreProduct storeProduct);

    List<StoreProduct> selectProduct();
}
