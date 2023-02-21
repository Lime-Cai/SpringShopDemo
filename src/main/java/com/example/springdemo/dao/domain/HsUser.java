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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName hs_user
 */
@TableName(value ="hs_user")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HsUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 帳號
     */
    private String username;

    /**
     * 密碼
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 郵件
     */
    private String mail;

    /**
     * 電話
     */
    private Integer phone;

    /**
     * 
     */
    private Integer isStore;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private LocalDateTime lastLoginTime;

    /**
     * 
     */
    private String token;

    /**
     * 
     */
    private LocalDateTime addTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}