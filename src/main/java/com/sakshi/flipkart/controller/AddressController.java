package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.AddressDto;
import com.sakshi.flipkart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(addressService.addAddress(addressDto), HttpStatus.CREATED);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long addressId){
        return new ResponseEntity<AddressDto>(addressService.getAddress(addressId),HttpStatus.OK);
    }
}
