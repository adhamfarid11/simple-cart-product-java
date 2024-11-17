package com.wide_tech_interview.cart_product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private ProductTypeDTO productType;

    @Getter
    @Setter
    public static class ProductTypeDTO {
        private Long productTypeId;
        private String name;
    }
}
