package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.CartSummaryDto;
import com.sakshi.flipkart.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartSummeryServiceImpl implements CartService {

    @Override
    public CartSummaryDto addToCart(Long customerId, CartSummaryDto cartSummaryDto) {
        return null;
    }

    @Override
    public Boolean removeFromCart(Long cartId, Long productId) {
        return null;
    }

    @Override
    public Boolean increaseOrDecreaseCount(Long cartId, Long productId, Integer updatedCount) {
        return null;
    }

    @Override
    public List<CartSummaryDto> getActiveCart(Long cartId) {
        return null;
    }

    @Override
    public List<CartSummaryDto> getActiveCartByCustomerId(Long customerId) {
        return null;
    }
}
