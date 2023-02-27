package com.example.springdemo.service.impl;

import com.example.springdemo.dao.domain.StoreProduct;
import com.example.springdemo.dao.mapper.StoreProductMapper;
import com.example.springdemo.dao.repository.StoreProductRepository;
import com.example.springdemo.service.model.StoreProductService;
import com.example.springdemo.tools.SystemTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
public class StoreProductServiceImpl implements StoreProductService {

    @Autowired
    private StoreProductRepository storeProductRepository;
    @Autowired
    private StoreProductMapper storeProductMapper;
    @Autowired
    private SystemTools systemTools;

    @Override
    public StoreProduct add(StoreProduct storeProduct) throws NoSuchAlgorithmException {
        StoreProduct product = StoreProduct.builder()
                .adminId(storeProduct.getAdminId())
                .productId(systemTools.md5Token(storeProduct.getProductName()))
                .productName(storeProduct.getProductName())
                .type(storeProduct.getType())
                .amount(storeProduct.getAmount())
                .quantity(storeProduct.getQuantity())
                .describe(storeProduct.getDescribe())
                .creatTime(LocalDateTime.now())
                .status(storeProduct.getStatus())
                .hide(storeProduct.getHide())
                .remark(storeProduct.getRemark())
                .updateTime(LocalDateTime.now())
                .build();

        storeProductRepository.save(product);
        log.info("商品 : {} 新增成功 時間 : {}",storeProduct.getProductId(),storeProduct.getUpdateTime());
        return product;
    }

    @Override
    public void update(StoreProduct storeProduct) {
        StoreProduct product = StoreProduct.builder()
                .adminId(storeProduct.getAdminId())
                .productId(storeProduct.getProductId())
                .productName(storeProduct.getProductName())
                .type(storeProduct.getType())
                .amount(storeProduct.getAmount())
                .quantity(storeProduct.getQuantity())
                .describe(storeProduct.getDescribe())
                .creatTime(storeProduct.getCreatTime())
                .status(storeProduct.getStatus())
                .hide(storeProduct.getHide())
                .remark(storeProduct.getRemark())
                .updateTime(LocalDateTime.now())
                .build();
        storeProductMapper.updateProduct(product);
        log.info("商品 : {} 更新成功 時間 : {}",storeProduct.getProductId(),storeProduct.getUpdateTime());
    }

    @Override
    public void productHide(StoreProduct storeProduct) {

    }

    @Override
    public void updateStatus(StoreProduct storeProduct) {

    }

    @Override
    public List<StoreProduct> selectProduct() {
        return storeProductRepository.findAll();
    }
}
