package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="category_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName;
    private String description;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(
            name="parent_id",
            referencedColumnName = "categoryId"
    )
    private Category parent;
}
