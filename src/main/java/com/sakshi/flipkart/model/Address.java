package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="address_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String description;
    private Long pinCode;
    private String landmark;
    @JoinColumn(
            name="customer_id",
            referencedColumnName = "customerId"
    )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Customer customer;

}
