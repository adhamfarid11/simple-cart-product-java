package com.wide_tech_interview.cart_product.config;

import com.wide_tech_interview.cart_product.model.ProductType;
import com.wide_tech_interview.cart_product.repository.ProductTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductTypeInitializer {

    @Bean
    public CommandLineRunner initializeProductTypes(ProductTypeRepository productTypeRepository) {
        return args -> {
            if (productTypeRepository.count() == 0) {
                List<ProductType> productTypes = List.of(
                    new ProductType(1L, "Buku"),
                    new ProductType(2L, "Laptop"),
                    new ProductType(3L, "Pakaian"),
                    new ProductType(4L, "Makanan"),
                    new ProductType(5L, "Minuman")
                );

                productTypeRepository.saveAll(productTypes);
                System.out.println("Product types initialized successfully.");
            } else {
                System.out.println("Product types already initialized.");
            }
        };
    }
}
