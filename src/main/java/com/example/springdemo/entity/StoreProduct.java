package com.example.springdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer admin_id;
    private Integer product_id;
    private String product_name;
    private String type;
    private BigDecimal amount;
    private BigDecimal quantity;
    private String describe;
    private LocalDateTime creat_time;
    private Integer status;
    private Integer hide;
    private String remark;
    private LocalDateTime update_time;
}
