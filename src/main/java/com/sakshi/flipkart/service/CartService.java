package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.CartSummaryDto;

import java.util.List;

public interface CartService {

    CartSummaryDto addToCart(Long customerId, CartSummaryDto cartSummaryDto);

    Boolean removeFromCart(Long cartId, Long productId);

    Boolean increaseOrDecreaseCount(Long cartId, Long productId, Integer updatedCount);

    List<CartSummaryDto> getActiveCart(Long cartId);

    List<CartSummaryDto> getActiveCartByCustomerId(Long customerId);

}
