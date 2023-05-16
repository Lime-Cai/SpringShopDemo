package com.example.springdemo.dao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductEntity {

    private String productName;

    private String type;

    private BigDecimal amount;

    private BigDecimal quantity;

    private String describe;

    private Boolean hide;

}
