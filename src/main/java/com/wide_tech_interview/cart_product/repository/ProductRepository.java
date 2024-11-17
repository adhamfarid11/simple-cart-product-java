package com.wide_tech_interview.cart_product.repository;

import com.wide_tech_interview.cart_product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
