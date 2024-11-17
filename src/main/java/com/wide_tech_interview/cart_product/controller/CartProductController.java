package com.wide_tech_interview.cart_product.controller;

import com.wide_tech_interview.cart_product.dto.ApiResponse;
import com.wide_tech_interview.cart_product.dto.ProductRequestDTO;
import com.wide_tech_interview.cart_product.model.Cart;
import com.wide_tech_interview.cart_product.model.Product;
import com.wide_tech_interview.cart_product.service.CartService;
import com.wide_tech_interview.cart_product.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts") 
public class CartProductController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    private Cart getCart() {
        return cartRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // Endpoint to get all carts
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return ResponseEntity.ok(carts);
    }

    // Get paginated products in a specific cart
    @GetMapping("/{cartId}/products")
    public ResponseEntity<ApiResponse<Page<Product>>> getPaginatedProductsInCart(
            @PathVariable Long cartId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Product> paginatedProducts = cartService.getProductsInCart(cartId, page, size);
        String message = "Successfully retrieved the cart.";
        ApiResponse<Page<Product>> response = new ApiResponse<>(message, paginatedProducts);
        return ResponseEntity.ok(response);
    }

    // Endpoint to add a product to the cart
    @PostMapping("/products")
    public ResponseEntity<ApiResponse<Product>> addProductToCart(@RequestBody ProductRequestDTO productRequest) {
        String name = productRequest.getName();
        int price = productRequest.getPrice();
        Long productTypeId = productRequest.getProductTypeId();
        int quantity = productRequest.getQuantity();

        Cart cart = getCart(); 
        Product savedProduct = cartService.addProductToCart(cart, name, price, productTypeId, quantity);

        String message = "Successfully added product: " + name + " to the cart.";
        ApiResponse<Product> response = new ApiResponse<>(message, savedProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
