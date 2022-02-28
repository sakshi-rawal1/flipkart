package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.CompanyDto;

public interface CompanyService {
    CompanyDto addCompany(CompanyDto companyDto);
    CompanyDto deleteCompany(Long companyId);
    CompanyDto getCompany(Long companyId);
    CompanyDto updateCompany(Long companyId,CompanyDto companyDto);

}
