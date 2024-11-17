package com.wide_tech_interview.cart_product.service;

import org.springframework.data.domain.Page;

import com.wide_tech_interview.cart_product.model.Cart;
import com.wide_tech_interview.cart_product.model.Product;

public interface CartService {

    Cart addProductToCart(Cart cart, String name, int price, Long productTypeId, int quantity);
    public Page<Product> getProductsInCart(Long cartId, int page, int size);
}
