package com.wide_tech_interview.cart_product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wide_tech_interview.cart_product.model.ProcessedCart;
import com.wide_tech_interview.cart_product.model.Product;
import com.wide_tech_interview.cart_product.repository.ProcessedCartRepository;
import com.wide_tech_interview.cart_product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProcessedCartRepository processedCartRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProcessedCartRepository processedCartRepository) {
        this.productRepository = productRepository;
        this.processedCartRepository = processedCartRepository;
    }

    @Override
    @Transactional
    public void moveProductsToProcessedCart(Long cartId, List<Long> productIds) {
         // Validate input
        if (!processedCartRepository.existsById(cartId)) {
            throw new RuntimeException("Cart with ID " + cartId + " does not exist.");
        }

        List<Product> products = productRepository.findAllById(productIds);
        if (products.size() != productIds.size()) {
            throw new RuntimeException("Some products do not exist for the given IDs: " + productIds);
        }

        ProcessedCart processedCart = processedCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Processed Product Cart not found."));

        for (Product product : products) {
            product.setCart(null);
            product.setProcessedCart(processedCart);
        }

        productRepository.saveAll(products);
    }
    
}
