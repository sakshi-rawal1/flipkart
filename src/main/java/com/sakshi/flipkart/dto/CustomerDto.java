package com.sakshi.flipkart.dto;

import com.sakshi.flipkart.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    enum Gender{
        MALE,FEMALE;
    }
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailId;
    private String imageUrl;
    private String password;
    private Boolean status;
    private Gender gender;
}
