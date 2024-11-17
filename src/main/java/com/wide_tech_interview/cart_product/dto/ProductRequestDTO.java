package com.wide_tech_interview.cart_product.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private int price;
    private Long productTypeId;
    private int quantity;
}
