package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.AddressDto;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Address;
import com.sakshi.flipkart.model.Customer;
import com.sakshi.flipkart.repository.AddressRepository;
import com.sakshi.flipkart.repository.CustomerRepository;
import com.sakshi.flipkart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        AddressDto finalAddressDto = addressDto;
        Customer customer = customerRepository.findById(addressDto.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","customerId", finalAddressDto.getCustomerId().toString())
        );
        Address address=convertAddressDtoToAddress(addressDto);
        address = addressRepository.save(address);
        addressDto = convertAddressToAddressDto(address);
        return addressDto;
    }

    @Override
    public AddressDto getAddress(Long addressId) {
        return null;
    }

    @Override
    public AddressDto deleteAddress(Long addressId) {
        return null;
    }

    @Override
    public List<AddressDto> findByCustomerId(Long customerId) {
        return null;
    }
    public AddressDto convertAddressToAddressDto(Address address) {
        AddressDto addressDto = AddressDto.builder()
                .description(address.getDescription())
                .customerId(address.getCustomer().getCustomerId())
                .landmark(address.getLandmark())
                .pinCode(address.getPinCode()).build();
                return addressDto;
    }

    public Address convertAddressDtoToAddress(AddressDto addressDto) {
        Address address = Address.builder()
                .description(addressDto.getDescription())
                .description(addressDto.getDescription())
                .landmark(addressDto.getLandmark())
                .pinCode(addressDto.getPinCode())
                .customer(customerRepository.findById(addressDto.getCustomerId()).get()).build();
                return address;
    }
}
