package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.AddressDto;
import com.sakshi.flipkart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(addressService.addAddress(addressDto), HttpStatus.CREATED);
    }
}
