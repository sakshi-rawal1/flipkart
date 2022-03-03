package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.CompanyDto;
import com.sakshi.flipkart.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto){
        return new ResponseEntity<CompanyDto>(companyService.addCompany(companyDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<CompanyDto> deleteCompany(@PathVariable Long companyId){
        return new ResponseEntity<CompanyDto>(companyService.deleteCompany(companyId),HttpStatus.OK);
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long companyId){
        return new ResponseEntity<CompanyDto>(companyService.getCompany(companyId),HttpStatus.OK);
    }
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long companyId, @RequestBody CompanyDto companyDto){
        return new ResponseEntity<CompanyDto>(companyService.updateCompany(companyId,companyDto),HttpStatus.OK);
    }
}
