package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private Double price;
    private String description;
    @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Image> images;
    @JoinColumn(
            name="company_id",
            referencedColumnName = "companyId"
    )
    @OneToOne(cascade = CascadeType.DETACH)
    private Company company;
    @JoinColumn(
            name="category_id",
            referencedColumnName = "categoryId"
    )
    @OneToOne(cascade = CascadeType.DETACH)
    private Category category;

}
