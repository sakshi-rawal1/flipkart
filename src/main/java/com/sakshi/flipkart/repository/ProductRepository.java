package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
