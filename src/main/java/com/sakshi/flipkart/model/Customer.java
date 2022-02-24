package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    enum Gender{
        MALE,FEMALE;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String emailId;
    private String imageUrl;
    private String password;
    private Boolean status;
    private Gender gender;
    @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Address> addresses;
}
