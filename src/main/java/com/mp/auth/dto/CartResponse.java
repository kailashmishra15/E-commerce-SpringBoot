package com.mp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponse {

    private Long id;

    private Long productId;

    private String productName;

    private String imageUrl;

    private Double price;

    private Integer quantity;
}
