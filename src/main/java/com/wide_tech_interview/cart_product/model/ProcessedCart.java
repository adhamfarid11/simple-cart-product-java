package com.wide_tech_interview.cart_product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ProcessedCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "processedCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
