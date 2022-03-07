package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.ProductDto;

import java.util.List;


public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    ProductDto getProduct(Long productId);

    List<ProductDto> getProductByCompanyName(String companyName);

    ProductDto getProductsByName(String productName);

    List<ProductDto> searchProducts(String regex);

    ProductDto deleteProduct(Long productId);

    List<ProductDto> getProductByCategoryId(Long categoryId);

    List<ProductDto> getProductByPriceRange(Double startPrice, Double endPrice);

    List<ProductDto> getProductSortByPrice(Integer pageNumber);

}
