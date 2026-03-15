package com.mp.auth.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mp.auth.model.Product;
import com.mp.auth.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}