package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.CompanyDto;
import com.sakshi.flipkart.exception.ResourceIsExistException;
import com.sakshi.flipkart.model.Company;
import com.sakshi.flipkart.repository.CompanyRepository;
import com.sakshi.flipkart.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        companyRepository.findByCompanyName(companyDto.getCompanyName()).ifPresent(
                (i) -> {
                    throw new ResourceIsExistException("Company", "companyName", i.getCompanyName());
                }
        );

        return null;
    }

    @Override
    public CompanyDto deleteCompany(Long companyId) {
        return null;
    }

    @Override
    public CompanyDto getCompany(Long companyId) {
        return null;
    }

    @Override
    public CompanyDto updateCompany(Long companyId, CompanyDto companyDto) {
        return null;
    }

    public Company convertCompanyDtoToCompany(CompanyDto companyDto) {
//        Company company = Company.builder().companyName(companyDto.getCompanyName())
//                .description(companyDto.getDescription())
//                .address()
        return null;
    }

}
