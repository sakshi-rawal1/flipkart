package com.sakshi.flipkart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartSummaryDto {
    private Long cartId;
    private Long productId;
    private Long quantity;
    private Double price;

}
