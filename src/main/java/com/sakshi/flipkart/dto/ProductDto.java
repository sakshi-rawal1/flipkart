package com.sakshi.flipkart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private String productName;
    private Double price;
    private String description;
    private Long companyId;
    private Long categoryId;
    //private List<ImageDto> imageDtos;

}
