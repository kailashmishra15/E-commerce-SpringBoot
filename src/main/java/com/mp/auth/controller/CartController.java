package com.mp.auth.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mp.auth.dto.CartResponse;
import com.mp.auth.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin
public class CartController {

    private final CartService cartService;

    // Add product to cart
    @PostMapping("/add")
    public String add(
            @RequestParam("email") String email,
            @RequestParam("productId") Long productId) {

        cartService.addToCart(email, productId);
        return "Product added to cart";
    }

    // Get cart items for a user
    @GetMapping("/{email}")
    public List<CartResponse> cart(@PathVariable("email") String email){

        return cartService.getCart(email);

    }

    // Remove item from cart
    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id){

        cartService.remove(id);
        return "Item removed from cart";

    }
    @PostMapping("/increase/{id}")
    public void increase(@PathVariable("id") Long id){
        cartService.increase(id);
    }

    @PostMapping("/decrease/{id}")
    public void decrease(@PathVariable("id") Long id){
        cartService.decrease(id);
    }

}
