package com.example.springdemo.service.model;

import com.example.springdemo.dao.domain.HsUser;
import com.example.springdemo.dao.domain.StoreProduct;
import com.example.springdemo.dao.domain.entity.StoreProductEntity;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface StoreProductService {

    StoreProduct add(String token, StoreProductEntity storeProduct);

    void update(StoreProduct storeProduct);

    void productHide(String productId,String token);

    void updateStatus(StoreProduct storeProduct);

    List<StoreProduct> selectProduct();

    List<StoreProduct> selectProduct(HsUser hsUser);

    void download(HttpServletResponse response, String token);
}
