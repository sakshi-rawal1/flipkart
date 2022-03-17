package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "oder_tb")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Instant timestamp;
    private Double totalPrice;
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId"
    )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Customer customer;
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cartId"
    )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Cart cart;
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "addressId"
    )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Address address;

}