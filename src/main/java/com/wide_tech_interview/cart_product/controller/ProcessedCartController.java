package com.wide_tech_interview.cart_product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wide_tech_interview.cart_product.dto.ApiResponse;
import com.wide_tech_interview.cart_product.dto.ProductResponseDTO;
import com.wide_tech_interview.cart_product.mapper.ProductMapper;
import com.wide_tech_interview.cart_product.model.Cart;
import com.wide_tech_interview.cart_product.model.ProcessedCart;
import com.wide_tech_interview.cart_product.model.Product;
import com.wide_tech_interview.cart_product.model.ProductType;
import com.wide_tech_interview.cart_product.repository.CartRepository;
import com.wide_tech_interview.cart_product.repository.ProcessedCartRepository;
import com.wide_tech_interview.cart_product.repository.ProductTypeRepository;
import com.wide_tech_interview.cart_product.service.CartService;
import com.wide_tech_interview.cart_product.service.ProcessedCartService;

@RestController
@RequestMapping("/api/processed-carts") 
public class ProcessedCartController {
    
    @Autowired
    private ProcessedCartService processedCartService;

    @Autowired
    private ProcessedCartRepository processedCartRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductMapper productMapper;

    // Endpoint to get all processed carts
    @GetMapping
    public ResponseEntity<List<ProcessedCart>> getAllCarts() {
        List<ProcessedCart> carts = processedCartRepository.findAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{cartId}/products")
    public ResponseEntity<ApiResponse<Page<ProductResponseDTO>>> getPaginatedProductsInCart(
            @PathVariable Long cartId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
    
        Page<Product> paginatedProducts = processedCartService.getProductsInCart(cartId, page, size);
    
        Page<ProductResponseDTO> productResponseDTOPage = paginatedProducts.map(product -> {
            ProductType productType = productTypeRepository.findById(product.getProductTypeId())
                    .orElseThrow(() -> new RuntimeException("Product type not found for ID: " + product.getProductTypeId()));
            return productMapper.mapToResponse(product, productType);
        });
    
        String message = "Successfully retrieved the processed cart products.";
        ApiResponse<Page<ProductResponseDTO>> response = new ApiResponse<>(message, productResponseDTOPage);
    
        return ResponseEntity.ok(response);
    }
}
