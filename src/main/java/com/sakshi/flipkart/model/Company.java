package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="company_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long companyId;
    private String companyName;
    private String ImageUrl;
    private String description;
    private String websiteUrl;
    private Long phoneNumber;
    private String emailId;
    @JoinColumn(
            name="address_id",
            referencedColumnName = "addressId"
    )
    @OneToOne(cascade = CascadeType.DETACH)
    private Address address;

}
