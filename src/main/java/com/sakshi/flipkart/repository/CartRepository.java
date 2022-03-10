package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
