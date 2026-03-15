package com.mp.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mp.auth.dto.CartResponse;
import com.mp.auth.model.CartItem;
import com.mp.auth.model.Product;
import com.mp.auth.repository.CartRepository;
import com.mp.auth.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public void addToCart(String email, Long productId){

        Optional<CartItem> existing =
                cartRepository.findByUserEmailAndProductId(email,productId);

        if(existing.isPresent()){

            CartItem item = existing.get();
            item.setQuantity(item.getQuantity()+1);
            cartRepository.save(item);

        }else{

            CartItem cart = new CartItem();
            cart.setUserEmail(email);
            cart.setProductId(productId);
            cart.setQuantity(1);

            cartRepository.save(cart);
        }

    }

    public List<CartResponse> getCart(String email){

        List<CartItem> items = cartRepository.findByUserEmail(email);

        return items.stream().map(item -> {

            Product p = productRepository.findById(item.getProductId()).orElseThrow();

            return new CartResponse(
                    item.getId(),
                    p.getId(),
                    p.getName(),
                    p.getImageUrl(),
                    p.getPrice(),
                    item.getQuantity()
            );

        }).toList();
    }

    public void remove(Long id){
        cartRepository.deleteById(id);
    }
    public void increase(Long id){

        CartItem item = cartRepository.findById(id).orElseThrow();

        item.setQuantity(item.getQuantity() + 1);

        cartRepository.save(item);

    }

    public void decrease(Long id){

        CartItem item = cartRepository.findById(id).orElseThrow();

        if(item.getQuantity() <= 1){
            cartRepository.delete(item);
        }else{
            item.setQuantity(item.getQuantity() - 1);
            cartRepository.save(item);
        }

    }

}

