package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "SELECT * from cart_tb WHERE cart_id = ?1 AND status = true",nativeQuery = true)
    Optional<Cart> findByIdAndStatus(Long cartId);

    @Query(value = "SELECT * from cart_tb where customer_id = ?1 AND status = true",nativeQuery = true)
    Optional<Cart> checkActiveCart(Long customerId);


}
