package com.wide_tech_interview.cart_product.repository;

import org.springframework.stereotype.Repository;

import com.wide_tech_interview.cart_product.model.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CartProductRepository extends JpaRepository<CartItem, Long> {
    
}
