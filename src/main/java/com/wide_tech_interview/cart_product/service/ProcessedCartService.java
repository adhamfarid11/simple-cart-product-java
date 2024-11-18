package com.wide_tech_interview.cart_product.service;

import org.springframework.data.domain.Page;

import com.wide_tech_interview.cart_product.model.Product;

public interface ProcessedCartService {
    public Page<Product> getProductsInCart(Long cartId, int page, int size);
}
