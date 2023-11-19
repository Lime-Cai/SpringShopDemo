package com.example.springdemo.service.model;

import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.entity.StoreProduct;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface StoreProductService {


    StoreProduct add(String token, StoreProduct storeProduct);

    void update(StoreProduct storeProduct);

    void productHide(String productId,String token);

    void updateStatus(StoreProduct storeProduct);

    List<StoreProduct> selectProduct();

    List<StoreProduct> selectProduct(HsUser hsUser);

    void download(HttpServletResponse response, String token);
}
