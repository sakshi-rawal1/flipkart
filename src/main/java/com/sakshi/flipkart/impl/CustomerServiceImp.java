package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.CustomerDto;
import com.sakshi.flipkart.repository.CustomerRepository;
import com.sakshi.flipkart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto singUp(CustomerDto customerDto) {
        return null;
    }

    @Override
    public CustomerDto getCustomer(Long customerId) {
        return null;
    }

    @Override
    public CustomerDto deleteCustomer(Long customerId) {
        return null;
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        return null;
    }
}
