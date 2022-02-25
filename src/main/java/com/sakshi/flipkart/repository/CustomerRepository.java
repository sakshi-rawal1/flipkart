package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
