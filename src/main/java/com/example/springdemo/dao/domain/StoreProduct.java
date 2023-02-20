package com.example.springdemo.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品
 * @TableName store_product
 */
@TableName(value ="store_product")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreProduct implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * admin_id
     */
    private Integer adminId;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 類型
     */
    private String type;

    /**
     * 價格
     */
    private Integer amount;

    /**
     * 數量
     */
    private Integer quantity;

    /**
     * 描述
     */
    private String describe;

    /**
     * 新增時間
     */
    private LocalDateTime creatTime;

    /**
     * 0.下架、1.上架、2.庫存=0
     */
    private Integer status;

    /**
     * 0.隱藏、1.顯示
     */
    private Integer hide;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间/回调时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}