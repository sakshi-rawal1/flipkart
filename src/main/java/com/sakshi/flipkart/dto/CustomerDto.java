package com.sakshi.flipkart.dto;

import com.sakshi.flipkart.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailId;
    private String imageUrl;
    private String password;
    private Boolean status;
    private Customer.Gender gender;
}
