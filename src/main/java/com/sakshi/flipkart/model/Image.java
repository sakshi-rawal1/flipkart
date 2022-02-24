package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="image_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;
    private String imageUrl;
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "productId"
    )
    @ManyToOne(cascade = CascadeType.DETACH)
    private Product product;


}
