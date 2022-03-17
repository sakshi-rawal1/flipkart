package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="cart_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public Cart(Boolean status, Customer customer) {
        this.status = status;
        this.customer = customer;
    }
}
