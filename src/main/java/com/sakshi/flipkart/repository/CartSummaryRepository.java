package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.CartSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartSummaryRepository extends JpaRepository<CartSummary,Long> {
}
