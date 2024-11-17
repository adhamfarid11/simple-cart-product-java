package com.wide_tech_interview.cart_product.mapper;

import com.wide_tech_interview.cart_product.dto.ProductResponseDTO;
import com.wide_tech_interview.cart_product.model.Product;
import com.wide_tech_interview.cart_product.model.ProductType;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductResponseDTO mapToResponse(Product product, ProductType productType) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        ProductResponseDTO.ProductTypeDTO productTypeDTO = new ProductResponseDTO.ProductTypeDTO();
        productTypeDTO.setProductTypeId(productType.getId());
        productTypeDTO.setName(productType.getName());

        dto.setProductType(productTypeDTO);
        return dto;
    }
}
