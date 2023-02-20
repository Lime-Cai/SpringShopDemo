package com.example.springdemo.dao.mapper;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import com.example.springdemo.dao.domain.StoreProduct;

/**
* @author jp098
* @description 针对表【store_product(商品)】的数据库操作Mapper
* @createDate 2023-02-20 01:12:46
* @Entity com.example.springdemo.dao.domain.StoreProduct
*/
public interface StoreProductMapper {
    List<StoreProduct> selectAllByAdminId(@Param("adminId") Integer adminId);




}




