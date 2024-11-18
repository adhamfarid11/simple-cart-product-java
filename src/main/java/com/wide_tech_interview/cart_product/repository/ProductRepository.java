package com.wide_tech_interview.cart_product.repository;

import com.wide_tech_interview.cart_product.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
