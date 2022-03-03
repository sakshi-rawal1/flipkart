package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.CompanyDto;
import com.sakshi.flipkart.exception.ResourceIsExistException;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Company;
import com.sakshi.flipkart.repository.CompanyRepository;
import com.sakshi.flipkart.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Company company = convertCompanyDtoToCompany(companyDto);
        company= companyRepository.save(company);
        CompanyDto companyDto1 = convertCompanyToCompanyDto(company);
        return companyDto1;
    }

    @Override
    @Transactional
    public CompanyDto deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                ()->new ResourceNotFoundException("Company","companyId",companyId.toString())
        );
        CompanyDto companyDto = convertCompanyToCompanyDto(company);
        companyRepository.deleteById(companyId);
        return companyDto;
    }

    @Override
    public CompanyDto getCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                ()->new ResourceNotFoundException("Company","companyId",companyId.toString())
        );
        CompanyDto companyDto = convertCompanyToCompanyDto(company);
        return companyDto;
    }

    @Override
    public CompanyDto updateCompany(Long companyId, CompanyDto companyDto) {
        companyRepository.findById(companyId).orElseThrow(
                ()->new ResourceNotFoundException("Company","companyId",companyId.toString())
        );
        Company company = convertCompanyDtoToCompany(companyDto);
        company = companyRepository.save(company);
        companyDto = convertCompanyToCompanyDto(company);
        return companyDto;
    }

    private CompanyDto convertCompanyToCompanyDto(Company company) {
        return CompanyDto.builder()
                .companyName(company.getCompanyName())
                .description(company.getDescription())
                .emailId(company.getEmailId())
                .ImageUrl(company.getImageUrl())
                .websiteUrl(company.getWebsiteUrl())
                .phoneNumber(company.getPhoneNumber())
                .address(company.getAddress()).build();
    }

    public Company convertCompanyDtoToCompany(CompanyDto companyDto) {
        Company company = Company.builder().companyName(companyDto.getCompanyName())
                .description(companyDto.getDescription())
                .address(companyDto.getAddress())
                .phoneNumber(companyDto.getPhoneNumber())
                .websiteUrl(companyDto.getWebsiteUrl())
                .emailId(companyDto.getEmailId())
                .ImageUrl(companyDto.getImageUrl()).build();

        return company;
    }

}
