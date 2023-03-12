package com.example.springdemo.controller;

import com.example.springdemo.dao.domain.StoreProduct;
import com.example.springdemo.dao.domain.entity.ProductTypeEnum;
import com.example.springdemo.dao.domain.entity.StoreProductEntity;
import com.example.springdemo.service.model.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/product")

public class product {

    @Autowired
    private StoreProductService storeProductService;


    @GetMapping("/")
    public String index(@ModelAttribute StoreProduct storeProduct,@ModelAttribute StoreProductEntity storeProductEntity, Model model) throws NoSuchAlgorithmException {
        model.addAttribute("storeProduct" ,new StoreProduct());
        model.addAttribute("productList",storeProductService.selectProduct());
        model.addAttribute("storeProductEntity" ,new StoreProductEntity());
        model.addAttribute("type" , ProductTypeEnum.values());
        model.addAttribute("_method" ,"POST");
        return "system/product/product";
    }

    @GetMapping("/add")
    public String addIndex(@ModelAttribute StoreProductEntity storeProductEntity, Model model ){
        model.addAttribute("storeProductEntity" ,new StoreProductEntity());
        model.addAttribute("type" , ProductTypeEnum.values());
        model.addAttribute("_method" ,"POST");
        return "system/product/add_product";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute StoreProductEntity storeProductEntity, @CookieValue(value = "login_")String token) {
        storeProductService.add(token,storeProductEntity);
        return "system/product/product";
    }

    @GetMapping("/update/{token}")
    public String update(@PathVariable("token") String ProductToken ,Model model){
        model.addAttribute("_method","POST");
        return "system/product/product";
    }
}
