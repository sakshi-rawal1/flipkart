package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.ProductDto;
import com.sakshi.flipkart.service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<ProductDto>(productService.addProduct(productDto), HttpStatus.CREATED);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){
        return new ResponseEntity<ProductDto>(productService.getProduct(productId),HttpStatus.OK);
    }
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<ProductDto>> getProductByCompanyName(@PathVariable String companyName){
        return new ResponseEntity<List<ProductDto>>(productService.getProductByCompanyName(companyName),HttpStatus.OK);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long productId){
        return new ResponseEntity<ProductDto>(productService.deleteProduct(productId),HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryId(@PathVariable Long categoryId){
        return new ResponseEntity<List<ProductDto>>(productService.getProductByCategoryId(categoryId),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProductByPriceRange(@PathParam("startPrice") Double startPrice, @PathParam("endPrice") Double endPrice){
        return new ResponseEntity<List<ProductDto>>(productService.getProductByPriceRange(startPrice,endPrice),HttpStatus.OK);
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductDto> getProductsByName(@PathVariable String productName){
        return new ResponseEntity<ProductDto>(productService.getProductsByName(productName),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathParam("regex") String regex){
        return new ResponseEntity<List<ProductDto>>(productService.searchProducts(regex),HttpStatus.OK);
    }
}
