package com.wide_tech_interview.cart_product.service;

import java.util.List;

public interface ProductService {
    void moveProductsToProcessedCart(Long cartId, List<Long> productIds);
}
