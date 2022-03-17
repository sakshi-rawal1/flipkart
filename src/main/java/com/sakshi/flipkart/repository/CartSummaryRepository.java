package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Cart;
import com.sakshi.flipkart.model.CartSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartSummaryRepository extends JpaRepository<CartSummary,Long> {
    @Query(value = "SELECT * from cartsummary_tb where cart_id = ?1 and product_id = ?2",nativeQuery = true)
    Optional<CartSummary> findByCartAndProduct(Long cartId, Long productId);

    Optional<List<CartSummary>> findByCartCartId(Long cartId);

    @Query(value = "SELECT * from cartsummary_tb where cart_id IN (SELECT cart_id from cart_tb where customer_id = ?1)",nativeQuery = true)
    Optional<List<CartSummary>> findByCustomerId(Long customerId);
}
