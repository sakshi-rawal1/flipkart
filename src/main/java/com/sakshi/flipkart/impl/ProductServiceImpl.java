package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.ImageDto;
import com.sakshi.flipkart.dto.ProductDto;
import com.sakshi.flipkart.exception.ResourceIsExistException;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Category;
import com.sakshi.flipkart.model.Company;
import com.sakshi.flipkart.model.Image;
import com.sakshi.flipkart.model.Product;
import com.sakshi.flipkart.repository.CategoryRepository;
import com.sakshi.flipkart.repository.CompanyRepository;
import com.sakshi.flipkart.repository.ImageRepository;
import com.sakshi.flipkart.repository.ProductRepository;
import com.sakshi.flipkart.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        ProductDto finalProductDto = productDto;
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", finalProductDto.getCategoryId().toString())
        );
        Company company = companyRepository.findById(productDto.getCompanyId()).orElseThrow(
                () -> new ResourceNotFoundException("Company", "companyId", finalProductDto.getCompanyId().toString())
        );
        productRepository.findByProductName(productDto.getProductName()).ifPresent(
                i -> {
                    throw new ResourceIsExistException("Product", "productName", finalProductDto.getProductName());
                }
        );
        Product product = convertDtoToEntity(productDto);
        product = productRepository.save(product);
        Product finalProduct = product;
        List<ImageDto> images = productDto.getImageUrl().stream()
                .map(imageUrl -> new ImageDto(imageUrl, finalProduct.getProductId())).collect(Collectors.toList());
        List<Image> list = new ArrayList<>();
        for (ImageDto imageDto : images) {
            Image image = new Image();
            BeanUtils.copyProperties(imageDto, image);
            list.add(image);
        }
        imageRepository.saveAll(list);
        productDto = convertEntityToDto(product);
        return productDto;
    }

    @Override
    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId", productId.toString())
        );
        ProductDto productDto = convertEntityToDto(product);
        return productDto;
    }

    @Override
    public List<ProductDto> getProductByCompanyName(String companyName) {
        Company company = companyRepository.findByCompanyName(companyName).orElseThrow(
                () -> new ResourceNotFoundException("Company", "companyName", companyName)
        );
        List<Product> products = productRepository.findByCompanyCompanyName(company).get();
        List<ProductDto> productDtos = products.stream().map(i -> convertEntityToDto(i)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public ProductDto getProductsByName(String productName) {
        Product product = productRepository.findByProductName(productName).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productName", productName)
        );
        ProductDto productDto = convertEntityToDto(product);
        return productDto;
    }

    @Override
    public List<ProductDto> searchProducts(String regex) {

        List<Product> products = productRepository.findByProductNameLike(regex).get();
        List<ProductDto> productDtos = products.stream().map(i->convertEntityToDto(i)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    @Transactional
    public ProductDto deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId", productId.toString())
        );
        ProductDto productDto = convertEntityToDto(product);
        productRepository.deleteById(productId);
        return productDto;
    }

    @Override
    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", categoryId.toString())
        );
        List<Product> products = productRepository.findByCategoryCategoryId(categoryId).get();
        List<ProductDto> productDtos = products.stream().map(i -> convertEntityToDto(i)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getProductByPriceRange(Double startPrice, Double endPrice) {
        List<Product> products = productRepository.findByPriceRange(startPrice, endPrice).get();
        List<ProductDto> productDtos = products.stream().map(i -> convertEntityToDto(i)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getProductSortByPrice(Integer pageNumber) {
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber,2,Sort.by(Sort.Direction.ASC, "price")));
        List<Product> productList = products.toList();
        List<ProductDto> productDtos=productList.stream().map(i->convertEntityToDto(i)).collect(Collectors.toList());
        return productDtos;
    }

    private ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .productName(product.getProductName())
                .companyId(product.getCompany().getCompanyId())
                .categoryId(product.getCategory().getCategoryId())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        return productDto;
    }

    private Product convertDtoToEntity(ProductDto productDto) {
        Product product = Product.builder()
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(categoryRepository.getOne(productDto.getCategoryId()))
                .company(companyRepository.getById(productDto.getCompanyId()))
                .build();
        return product;
    }
}
