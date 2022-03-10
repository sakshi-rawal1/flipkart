package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="cartsummary_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartSummaryId;
    private Long quantity;
    private Double price;
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cartId"
    )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Cart cart;
    @JoinColumn(
            name="product_id",
            referencedColumnName = "productId"
    )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Product product;

}
