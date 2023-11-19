package com.example.springdemo.tools.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductView {

    private String storeName;

    private List<product> productMaterial;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class product {
        private String productName;
        private String type;
        private BigDecimal amount;
        private BigDecimal quantity;
        private String describe;


    }
}
