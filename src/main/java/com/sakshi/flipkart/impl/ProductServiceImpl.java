package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.ProductDto;
import com.sakshi.flipkart.model.Category;
import com.sakshi.flipkart.repository.CategoryRepository;
import com.sakshi.flipkart.repository.CompanyRepository;
import com.sakshi.flipkart.repository.ProductRepository;
import com.sakshi.flipkart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return null;
    }

    @Override
    public List<ProductDto> getProductByCompanyName(String companyName) {
        return null;
    }

    @Override
    public List<ProductDto> getProductsByName(String name) {
        return null;
    }

    @Override
    public List<ProductDto> searchProducts(String regex) {
        return null;
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        return null;
    }

    @Override
    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public List<ProductDto> getProductByPriceRange(Double startPrice, Double endPrice) {
        return null;
    }

    @Override
    public List<ProductDto> getProductSortByPrice(Integer pageNumber) {
        return null;
    }
}
