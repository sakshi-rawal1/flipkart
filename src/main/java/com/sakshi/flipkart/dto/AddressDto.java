package com.sakshi.flipkart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String description;
    private Long pinCode;
    private String landmark;
    private Long customerId;
}
