package com.example.springdemo.dao.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.springdemo.dao.entity.StoreProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author jp098
* @description 针对表【store_product(商品)】的数据库操作Mapper
* @createDate 2023-02-25 17:00:04
* @Entity com.example.springdemo.dao.domain.StoreProduct
*/
@Mapper
@Repository
public interface StoreProductMapper {

    int updateProduct(StoreProduct storeProduct);

    int insertSelective(StoreProduct storeProduct);

    List<StoreProduct> selectByAdminIdStoreProduct(@Param("adminId") Integer adminId);
}




