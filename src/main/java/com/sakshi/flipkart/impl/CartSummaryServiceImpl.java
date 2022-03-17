package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.CartSummaryDto;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Cart;
import com.sakshi.flipkart.model.CartSummary;
import com.sakshi.flipkart.model.Customer;
import com.sakshi.flipkart.model.Product;
import com.sakshi.flipkart.repository.CartRepository;
import com.sakshi.flipkart.repository.CartSummaryRepository;
import com.sakshi.flipkart.repository.CustomerRepository;
import com.sakshi.flipkart.repository.ProductRepository;
import com.sakshi.flipkart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartSummaryServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartSummaryRepository cartSummaryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartSummaryDto addToCart(Long customerId, CartSummaryDto cartSummaryDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "cusromerId", customerId.toString()));
        CartSummaryDto finalCartSummaryDto = cartSummaryDto;
        Product product = productRepository.findById(cartSummaryDto.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId", finalCartSummaryDto.getProductId().toString()));
        Cart cart = cartRepository.checkActiveCart(customerId).orElseGet(
                () -> {
                    Cart newCart = new Cart(true, customer);
                    newCart = cartRepository.save(newCart);
                    return newCart;
                });
        CartSummary cartSummary = new CartSummary();
        cartSummary = convertDtoToEntity(cartSummaryDto);
        cartSummary = cartSummaryRepository.save(cartSummary);
        cartSummaryDto = convertEntityToDto(cartSummary);
        return cartSummaryDto;
    }


    @Override
    @Transactional
    public Boolean removeFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new ResourceNotFoundException("Cart", "cartId", cartId.toString()));
        CartSummary cartSummary = cartSummaryRepository.findByCartAndProduct(cart.getCartId(), productId).orElseThrow(
                () -> new ResourceNotFoundException("CartSummeryItem", "cartId", cartId.toString()));
        cartSummaryRepository.delete(cartSummary);
        return true;
    }

    @Override
    public Boolean increaseOrDecreaseCount(Long cartId, Long productId, Integer updatedCount) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                ()-> new ResourceNotFoundException("Cart","cartId",cartId.toString())
        );
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException("Product","productId",productId.toString())
        );
        CartSummary cartSummary = cartSummaryRepository.findByCartAndProduct(cartId,productId).orElseThrow(
                ()-> new ResourceNotFoundException("Cart","cartId",cartId.toString())
        );
        cartSummary.setQuantity(cartSummary.getQuantity()+updatedCount);
        if(cartSummary.getQuantity() < 1){
            cartSummaryRepository.deleteById(cartSummary.getProduct().getProductId());
        } else {
            cartSummary = cartSummaryRepository.save(cartSummary);
        }
        return true;
    }

    @Override
    public List<CartSummaryDto> getActiveCart(Long cartId) {
        Cart cart = cartRepository.findByIdAndStatus(cartId).orElseThrow(
                ()-> new ResourceNotFoundException("Cart","cartId",cartId.toString())
        );
        List<CartSummary> cartSummaries = cartSummaryRepository.findByCartCartId(cartId).get();
        List<CartSummaryDto> cartSummaryDtos = cartSummaries.stream().map(i->convertEntityToDto(i)).collect(Collectors.toList());
        return cartSummaryDtos;
    }

    @Override
    public List<CartSummaryDto> getActiveCartByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","customerId",customerId.toString())
        );
        Cart cart = cartRepository.checkActiveCart(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","customerId",customerId.toString())
        );
        List<CartSummary> cartSummaries = cartSummaryRepository.findByCustomerId(customerId).get();
        List<CartSummaryDto> cartSummaryDtos= cartSummaries.stream().map(i-> convertEntityToDto(i)).collect(Collectors.toList());
        return cartSummaryDtos;
    }
    private CartSummaryDto convertEntityToDto(CartSummary cartSummary) {
        CartSummaryDto cartSummaryDto = CartSummaryDto.builder()
                .quantity(cartSummary.getQuantity())
                .price(cartSummary.getPrice())
                .cartId(cartSummary.getCart().getCartId())
                .productId(cartSummary.getProduct().getProductId())
                .build();
        return cartSummaryDto;
    }
    private CartSummary convertDtoToEntity(CartSummaryDto cartSummaryDto) {
        CartSummary cartSummary = CartSummary.builder()
                .cart(cartRepository.getById(cartSummaryDto.getCartId()))
                .product(productRepository.getById(cartSummaryDto.getProductId()))
                .price(cartSummaryDto.getPrice())
                .quantity(cartSummaryDto.getQuantity()).build();
        return cartSummary;
    }
}
