package com.sakshi.flipkart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private Double price;
    private String description;
    private Long companyId;
    private Long categoryId;
    List<ImageDto> imageDtos;

}
