package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.CategoryDto;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto deleteCategory(Long categoryId);
    
}
