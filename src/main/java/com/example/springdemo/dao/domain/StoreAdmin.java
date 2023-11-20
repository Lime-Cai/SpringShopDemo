package com.example.springdemo.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 賣家認證
 * @TableName store_admin
 */
@TableName(value ="store_admin")
@Data
public class StoreAdmin implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * admin_id
     */
    private Integer adminId;

    /**
     * 郵件
     */
    private String mail;

    /**
     * 郵件審查
     */
    private Integer mailReview;

    /**
     * 0.還未認證、1.已通過郵件認證、2.審核中、3.通過認證、4.認證失敗、5.封鎖
     */
    private Integer status;

    /**
     * 新增時間
     */
    private LocalDateTime creatTime;

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