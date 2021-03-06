package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<List<Address>> findByCustomerCustomerId(Long customerId);
}
