package com.wide_tech_interview.cart_product.service;

import com.wide_tech_interview.cart_product.model.Cart;
import com.wide_tech_interview.cart_product.model.Product;
import com.wide_tech_interview.cart_product.repository.CartRepository;
import com.wide_tech_interview.cart_product.repository.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProductsInCart(Long cartId, int page, int size) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<Product> products = cart.getProducts();

        // Paginate the products list manually
        int start = (int) PageRequest.of(page, size).getOffset();
        int end = Math.min((start + size), products.size());
        List<Product> paginatedProducts = products.subList(start, end);

        return new PageImpl<>(paginatedProducts, PageRequest.of(page, size), products.size());
    }

    @Override
    public Product addProductToCart(Cart cart, String name, int price, Long productTypeId, int quantity) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);
        newProduct.setCart(cart);
        newProduct.setProductTypeId(productTypeId);
    
        Product savedProduct = productRepository.save(newProduct);
    
        cart.getProducts().add(savedProduct); 
        cartRepository.save(cart);
    
        return savedProduct;
    }
}
