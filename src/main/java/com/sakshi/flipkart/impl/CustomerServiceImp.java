package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.CustomerDto;
import com.sakshi.flipkart.exception.ResourceIsExistException;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Customer;
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
        CustomerDto finalCustomerDto = customerDto;
        customerRepository.findByEmailId(customerDto.getEmailId()).ifPresent(
                i -> {
                    throw new ResourceIsExistException("Customer", "emailId", finalCustomerDto.getEmailId());
                }
        );
        Customer customer = convertDtoToEntity(customerDto);
        customer = customerRepository.save(customer);
        customerDto = convertEntityToDto(customer);
        return customerDto;
    }

    @Override
    public CustomerDto getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "customerId", customerId));
        CustomerDto customerDto = convertEntityToDto(customer);
        return customerDto;
    }

    @Override
    public CustomerDto deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "customerId", customerId));
        CustomerDto customerDto = convertEntityToDto(customer);
        customerRepository.deleteById(customerId);
        return customerDto;
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "customerId", customerId));
        customer = convertDtoToEntity(customerDto);
        customer = customerRepository.save(customer);
        customerDto = convertEntityToDto(customer);
        return customerDto;
    }

    public Customer convertDtoToEntity(CustomerDto customerDto) {
        Customer customer = Customer.builder().emailId(customerDto.getEmailId()).firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName()).gender(customerDto.getGender()).phoneNumber(customerDto.getPhoneNumber())
                .emailId(customerDto.getEmailId()).status(customerDto.getStatus()).password(customerDto.getPassword())
                .imageUrl(customerDto.getPassword()).build();
        return customer;
    }

    public CustomerDto convertEntityToDto(Customer customer) {
        CustomerDto customerDto = CustomerDto.builder().emailId(customer.getEmailId()).firstName(customer.getFirstName())
                .lastName(customer.getLastName()).gender(customer.getGender()).phoneNumber(customer.getPhoneNumber())
                .emailId(customer.getEmailId()).status(customer.getStatus()).password(customer.getPassword())
                .imageUrl(customer.getPassword()).build();
        return customerDto;
    }
}
