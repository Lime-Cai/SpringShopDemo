package com.example.springdemo.service.impl;

import com.example.springdemo.controller.system.excel;
import com.example.springdemo.dao.domain.HsUser;
import com.example.springdemo.dao.domain.StoreProduct;
import com.example.springdemo.dao.domain.entity.StoreProductEntity;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.mapper.StoreProductMapper;
import com.example.springdemo.dao.repository.StoreProductRepository;
import com.example.springdemo.service.model.StoreProductService;
import com.example.springdemo.tools.SystemTools;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.LinkedList;
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
    @Autowired
    private HsUserMapper hsUserMapper;


    @Override
    public StoreProduct add(String token, StoreProductEntity storeProduct) {

        HsUser user = hsUserMapper.selectOneByToken(token);
        StoreProduct product = StoreProduct.builder()
                .adminId(user.getId())
                .productId(SystemTools.uuidToken())
                .productName(storeProduct.getProductName())
                .type_(storeProduct.getType())
                .amount(storeProduct.getAmount())
                .quantity(storeProduct.getQuantity())
                .describe_(storeProduct.getDescribe())
                .creatTime(LocalDateTime.now())
                .status(0)
                .hide(storeProduct.getHide())
                .remark("創建成功")
                .updateTime(LocalDateTime.now())
                .build();

        storeProductMapper.insertSelective(product);
        log.info("新增成功 時間 : {} 商品 : {} ", product.getUpdateTime(), product.toString());
        return product;
    }

    @Override
    public void update(StoreProduct storeProduct) {
        StoreProduct product = StoreProduct.builder()
                .adminId(storeProduct.getAdminId())
                .productId(storeProduct.getProductId())
                .productName(storeProduct.getProductName())
                .type_(storeProduct.getType_())
                .amount(storeProduct.getAmount())
                .quantity(storeProduct.getQuantity())
                .describe_(storeProduct.getDescribe_())
                .creatTime(storeProduct.getCreatTime())
                .status(storeProduct.getStatus())
                .hide(storeProduct.getHide())
                .remark(storeProduct.getRemark())
                .updateTime(LocalDateTime.now())
                .build();
        storeProductMapper.updateProduct(product);
        log.info("商品 : {} 更新成功 時間 : {}", storeProduct.getProductId(), storeProduct.getUpdateTime());
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

    @Override
    public void download(HttpServletResponse response) {

        List<StoreProduct> list = storeProductRepository.findAll();
        Workbook workbook = null;

        List<String> headerNameList = new LinkedList<>();
        headerNameList.add("商品名");
        headerNameList.add("數量");
        headerNameList.add("金額");
        headerNameList.add("a");
        headerNameList.add("bbb");
        headerNameList.add("123");
        for (StoreProduct product : list) {
            List<List<String>> lists = toStringList(list);
            workbook = excel.RegisterStatisticResponseToExcel(workbook, response, lists, product.getProductName(), headerNameList);
        }

        excel.exportRegisterStatisticExcel(response, workbook);

    }

    public List<List<String>> toStringList(List<StoreProduct> list) {

        List<List<String>> lists = new LinkedList<>();
        List<String> stringList = new LinkedList<>();

        for (StoreProduct product : list) {
            stringList.add(product.getProductName());
            stringList.add(String.valueOf(product.getQuantity()));
            stringList.add(String.valueOf(product.getAmount()));

            lists.add(stringList);
        }

        return lists;
    }
}
