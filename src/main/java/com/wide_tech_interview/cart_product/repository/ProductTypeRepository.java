package com.wide_tech_interview.cart_product.repository;

import com.wide_tech_interview.cart_product.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    
}
