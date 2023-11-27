package com.example.springdemo.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName hs_user
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "hs_user")
public class HsUser implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *
     */
    @Column(name = "token", unique = true)
    private String token;

    /**
     * 帳號
     */
    @Column(name = "username", unique = true)
    private String username;

    /**
     * 密碼
     */
    @Column(name = "password")
    private String password;

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "varchar(255) default 'UNKNOWN'")
    private String name;

    /**
     * 郵件
     */
    @Column(name = "mail", columnDefinition = "varchar(255) default 'UNKNOWN'")
    private String mail;

    /**
     * 電話
     */
    @Column(name = "phone")
    private Integer phone;


    /**
     *
     */
    @Column(name = "is_store")
    private Integer isStore;

    /**
     * 状态 0未验证 1验证完成
     */
    @Column(name = "status", columnDefinition = "int default 0")
    private Integer status;

    /**
     * 1 账号已被封锁
     */
    @Column(name = "is_lock", columnDefinition = "boolean default false")
    private Boolean isLock;

    /**
     *
     */
    @Column(name = "add_time")
    private LocalDateTime addTime;

    /**
     *
     */
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}