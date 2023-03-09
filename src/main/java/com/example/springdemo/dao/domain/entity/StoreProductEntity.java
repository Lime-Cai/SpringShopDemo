package com.example.springdemo.dao.domain.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductEntity {

    private String productName;

    private String type;

    private Integer amount;

    private Integer quantity;

    private String describe;

    private Boolean hide;

}
