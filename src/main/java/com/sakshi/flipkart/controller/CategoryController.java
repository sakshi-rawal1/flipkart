package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.CategoryDto;
import com.sakshi.flipkart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<CategoryDto>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long categoryId){
        return new ResponseEntity<CategoryDto>(categoryService.deleteCategory(categoryId),HttpStatus.OK);
    }
}
