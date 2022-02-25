package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.CustomerDto;

public interface CustomerService {

    CustomerDto singUp(CustomerDto customerDto);

    CustomerDto getCustomer(Long customerId);

    CustomerDto deleteCustomer(Long customerId);

    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);

}
