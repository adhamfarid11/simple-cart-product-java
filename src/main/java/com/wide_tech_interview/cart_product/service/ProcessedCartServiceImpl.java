package com.wide_tech_interview.cart_product.service;

import com.wide_tech_interview.cart_product.model.ProcessedCart;
import com.wide_tech_interview.cart_product.model.Product;
import com.wide_tech_interview.cart_product.repository.ProcessedCartRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;

@Service
public class ProcessedCartServiceImpl implements ProcessedCartService {
    
    @Autowired
    private ProcessedCartRepository processedCartRepository;

    @Override
    public Page<Product> getProductsInCart(Long cartId, int page, int size) {
        ProcessedCart cart = processedCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Processed Cart not found"));

        List<Product> products = cart.getProducts();

        int start = (int) PageRequest.of(page, size).getOffset();
        int end = Math.min((start + size), products.size());
        List<Product> paginatedProducts = products.subList(start, end);

        return new PageImpl<>(paginatedProducts, PageRequest.of(page, size), products.size());
    }
}
