package com.example.springdemo.service.impl;

import com.example.springdemo.tools.customizeDate;
import com.example.springdemo.tools.ExcelToole;
import com.example.springdemo.dao.entity.HsUser;
import com.example.springdemo.dao.entity.StoreProduct;
import com.example.springdemo.dao.mapper.HsUserMapper;
import com.example.springdemo.dao.mapper.StoreProductMapper;
import com.example.springdemo.dao.repository.StoreProductRepository;
import com.example.springdemo.service.model.StoreProductService;
import com.example.springdemo.tools.SystemTools;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class StoreProductServiceImpl implements StoreProductService {

    private final StoreProductRepository storeProductRepository;
    private final StoreProductMapper storeProductMapper;
    private final HsUserMapper hsUserMapper;

    public StoreProductServiceImpl(HsUserMapper hsUserMapper, StoreProductRepository storeProductRepository, StoreProductMapper storeProductMapper) {
        this.hsUserMapper = hsUserMapper;
        this.storeProductRepository = storeProductRepository;
        this.storeProductMapper = storeProductMapper;
    }


    @Override
    public StoreProduct add(String token, StoreProduct storeProduct) {

        HsUser user = hsUserMapper.selectOneByToken(token);
        StoreProduct product = StoreProduct.builder()
                .adminId(user.getId())
                .productId(SystemTools.uuidToken())
                .productName(storeProduct.getProductName())
                //.type_(storeProduct.getType())
                .amount(storeProduct.getAmount())
                .quantity(storeProduct.getQuantity())
                //.describe_(storeProduct.getDescribe())
                .creatTime(LocalDateTime.now())
                .status(0)
                .hide(storeProduct.getHide())
                .remark("創建成功")
                .updateTime(LocalDateTime.now())
                .build();

        storeProductMapper.insertSelective(product);
        log.info("時間 : {} 商品 : {}  新增成功 ", product.getUpdateTime(), product.toString());
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
        log.info("  時間 : {} 商品 : {} 更新成功", storeProduct.getUpdateTime(), storeProduct.getProductId());
    }

    @Override
    public void productHide(String productId, String token) {

        StoreProduct product = storeProductRepository.findByProductId(productId, token).orElseGet(() -> {
            log.error(" 時間 : {} 商品 : {} 为空值", customizeDate.localToString(LocalDateTime.now()), productId);
            return StoreProduct.builder().productName("ERROR").build();
        });

        if (!Objects.equals(product.getProductName(), "ERROR")) {
            product.setStatus(9);
            storeProductMapper.updateProduct(product);
            log.info(" 時間 : {} 商品 : {} 已设为关闭", customizeDate.localToString(LocalDateTime.now()), productId);
        }
    }

    @Override
    public void updateStatus(StoreProduct storeProduct) {

    }

    @Override
    public List<StoreProduct> selectProduct() {
        return storeProductRepository.findAll();
    }

    @Override
    public List<StoreProduct> selectProduct(HsUser hsUser) {
        return Optional.ofNullable(storeProductRepository.findByAdminId(hsUser.getId())).orElseGet(ArrayList::new);
    }

    @Override
    public void download(HttpServletResponse response, String token) {

        HsUser hsUser = hsUserMapper.selectOneByToken(token);

        if (Optional.ofNullable(hsUser).isEmpty()) {

        }

        List<StoreProduct> list = storeProductRepository.findAll();

        //List<ProductView.Product> storeProductList = new LinkedList<>();


        List<List<String>> sheetNameList = new LinkedList<>();
        List<String> sheetName = new LinkedList<>();
        for (StoreProduct storeProduct : list) {
            sheetName.add(hsUser.getUsername());
            sheetNameList.add(sheetName);
        }
        List<List<String>> callViewList = new LinkedList<>();
        List<String> callView = new LinkedList<>();
        for (StoreProduct storeProduct : list) {
            callView.add(storeProduct.getProductName());
            callView.add(storeProduct.getType_());
            callView.add(storeProduct.getDescribe_());
            callView.add(String.valueOf(storeProduct.getAmount()));
            callView.add(String.valueOf(storeProduct.getQuantity()));
            callView.add(String.valueOf(storeProduct.getCreatTime()));
            callViewList.add(sheetName);
        }
        ExcelToole.ResponseToExcel(response, sheetNameList, headerName(), callViewList);
    }

    //public ProductView.Product toProductView(StoreProduct storeProduct) {
    //    return ProductView.Product.builder()
    //            .type(storeProduct.getType_())
    //            .amount(storeProduct.getAmount())
    //            .describe(storeProduct.getDescribe_())
    //            .productName(storeProduct.getProductName())
    //            .quantity(storeProduct.getQuantity())
    //            .build();
    //}

    // Header 命名
    public static List<String> headerName() {
        List<String> list = new LinkedList<>();
        list.add("appName");
        list.add("日期");
        list.add("OTP发送量");
        list.add("註冊量");
        list.add("相冊认证數");
        list.add("通讯录认证數");
        list.add("AppList认证數");
        list.add("短信通话认证數");
        list.add("实名认证數");
        list.add("紧急联系人认证數");
        list.add("Face id认证數");
        list.add("绑卡认证數");
        list.add("內部风控通过數");
        list.add("外部风控通过數");
        list.add("订单申请量");
        list.add("新客放款量");
        return list;
    }
}
