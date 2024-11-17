package com.wide_tech_interview.cart_product.config;

import com.wide_tech_interview.cart_product.model.Cart;
import com.wide_tech_interview.cart_product.repository.CartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartInitializer {

    @Bean
    public CommandLineRunner initializeCart(CartRepository cartRepository) {
        return args -> {
            if (cartRepository.count() == 0) {
                Cart cart = new Cart();
                cartRepository.save(cart);
                System.out.println("Cart initialized successfully.");
            } else {
                System.out.println("Cart already exists.");
            }
        };
    }
}
