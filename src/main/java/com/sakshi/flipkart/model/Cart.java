package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="cart_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    private Boolean status;
    @JoinColumn(
            name="customer_id",
            referencedColumnName = "customerId"
    )
    @OneToOne(cascade = CascadeType.DETACH)
    private Customer customer;
}
