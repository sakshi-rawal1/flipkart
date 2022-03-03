package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.AddressDto;
import com.sakshi.flipkart.dto.CompanyDto;
import com.sakshi.flipkart.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/customer")
    public CustomerDto getDemoCustomer(){
        return new CustomerDto();
    }
    @GetMapping("/address")
    public AddressDto getDemoAddress(){
        return new AddressDto();
    }
    @GetMapping("/company")
    public CompanyDto getDemoCompany(){ return new CompanyDto(); }

}

