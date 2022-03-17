package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "SELECT * from order_tb where customer_id = ?1 order by time_stamp desc",nativeQuery = true)
    Optional<Order> findByCustomerId(Long customerId);

    Optional<List<Order>> findByCustomerCustomerId(Long customerId);
}
