package com.example.springdemo.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 * @TableName store_product
 */
@TableName(value ="store_product")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RestController
public class StoreProduct implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * admin_id
     */
    private Integer adminId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 類型
     */
    private String type_;

    /**
     * 價格
     */
    private BigDecimal amount;

    /**
     * 數量
     */
    private BigDecimal quantity;

    /**
     * 描述
     */
    private String describe_;

    /**
     * 新增時間
     */
    private LocalDateTime creatTime;

    /**
     * 0.下架、1.上架、2.庫存=0、9.删除
     */
    private Integer status;

    /**
     * 0.隱藏、1.顯示
     */
    private Boolean hide;

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