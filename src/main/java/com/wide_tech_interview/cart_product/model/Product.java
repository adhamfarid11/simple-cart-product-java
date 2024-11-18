package com.wide_tech_interview.cart_product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int quantity;
    private Long productTypeId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = true)
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "processed_cart_id", nullable = true)
    @JsonIgnore
    private ProcessedCart processedCart;

    @Column(nullable = false)
    private int total;

    @PrePersist
    @PreUpdate
    public void updateTotal() {
        this.total = this.quantity * this.price;
    }
}
