package com.example.springdemo.controller;

import com.example.springdemo.dao.entity.StoreProduct;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.service.model.StoreProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/api/product")

public class product {

    private final StoreProductService storeProductService;
    private final HsUserMapper hsUserMapper;

    public product(StoreProductService storeProductService, HsUserMapper hsUserMapper) {
        this.storeProductService = storeProductService;
        this.hsUserMapper = hsUserMapper;
    }


    @GetMapping("/list")
    public List<StoreProduct> index(@CookieValue(value = "login_") String token)  {
        return storeProductService.selectProduct(hsUserMapper.selectOneByToken(token));
    }

    @PostMapping("/add")
    public void add(StoreProduct storeProductEntity, @CookieValue(value = "login_") String token, Model model) {
        storeProductService.add(token, storeProductEntity);
    }

    @PutMapping("/update/{token}")
    public void update() {
    }

    @PutMapping ("/delete/{productId}")
    public void delete(String productId,@CookieValue(value = "login_") String token){
        storeProductService.productHide(productId, token);
    }

    @GetMapping("/download")
    public String download(HttpServletResponse response, @CookieValue(value = "login_") String token) {
        storeProductService.download(response, token);
        return "system/product/product";
    }
}
