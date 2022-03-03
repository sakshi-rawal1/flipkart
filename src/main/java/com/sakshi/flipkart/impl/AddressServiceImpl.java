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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Address address = addressRepository.findById(addressId).orElseThrow(
                ()-> new ResourceNotFoundException("Address","addressId",addressId.toString())
        );
        AddressDto addressDto = convertAddressToAddressDto(address);
        return addressDto;
    }

    @Override
    @Transactional
    public AddressDto deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                ()-> new ResourceNotFoundException("Address","addressId",addressId.toString())
        );
        AddressDto addressDto = convertAddressToAddressDto(address);
        addressRepository.deleteById(addressId);
        return addressDto;
    }

    @Override
    public List<AddressDto> findByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","cutomerId",customerId.toString())
        );
        List<Address> address = addressRepository.findByCustomerCustomerId(customerId).get();
        List<AddressDto> addressDtos = address.stream().map(i->convertAddressToAddressDto(i)).collect(Collectors.toList());
        return addressDtos;
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
