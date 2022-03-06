package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.CategoryDto;
import com.sakshi.flipkart.exception.ResourceIsExistException;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Category;
import com.sakshi.flipkart.repository.CategoryRepository;
import com.sakshi.flipkart.service.CategoryService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        categoryRepository.findByCategoryName(categoryDto.getCategoryName()).ifPresent(
                (i)-> {
                    throw new ResourceIsExistException("Category","categoryName",i.getCategoryName());
                }
        );
        Category category = convertDtoToEntity(categoryDto);
        category=categoryRepository.save(category);
        categoryDto = convertEntityToDto(category);
        return categoryDto;
    }

    private CategoryDto convertEntityToDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .parentId(category.getParent()!=null? category.getParent().getCategoryId() : null)
                .build();
        return categoryDto;
    }

    private Category convertDtoToEntity(CategoryDto categoryDto) {
        Category category = Category.builder()
                .categoryName(categoryDto.getCategoryName())
                .description(categoryDto.getDescription())
                .parent(categoryDto.getParentId()!=null?categoryRepository.getOne(categoryDto.getParentId()):null)
                .build();
        return category;
    }

    @Override
    @Transactional
    public CategoryDto deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category","categoryId",categoryId.toString())
        );
        CategoryDto categoryDto=convertEntityToDto(category);
        categoryRepository.deleteById(categoryId);
        return categoryDto;
    }
}
