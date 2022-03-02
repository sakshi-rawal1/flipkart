package com.sakshi.flipkart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {
    private String companyName;
    private String ImageUrl;
    private String description;
    private String websiteUrl;
    private Long phoneNumber;
    private String emailId;
}
