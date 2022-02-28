package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto addAddress(AddressDto addressDto);
    AddressDto getAddress(Long addressId);
    AddressDto deleteAddress(Long addressId);
    List<AddressDto> findByCustomerId(Long customerId);

}
