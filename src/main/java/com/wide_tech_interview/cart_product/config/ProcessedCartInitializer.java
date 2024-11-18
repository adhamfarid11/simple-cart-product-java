package com.wide_tech_interview.cart_product.config;

import com.wide_tech_interview.cart_product.model.ProcessedCart;
import com.wide_tech_interview.cart_product.repository.ProcessedCartRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessedCartInitializer {

    @Bean
    public CommandLineRunner initializeProcessedCart(ProcessedCartRepository processedCartRepository) {
        return args -> {
            if (processedCartRepository.count() == 0) {
                ProcessedCart cart = new ProcessedCart();
                processedCartRepository.save(cart);
                System.out.println("Processed Cart initialized successfully.");
            } else {
                System.out.println("Processed Cart already exists.");
            }
        };
    }
}
