package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Company;
import com.sakshi.flipkart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);

    Optional<List<Product>> findByCompanyCompanyName(Company company);

    Optional<List<Product>> findByCategoryCategoryId(Long categoryId);

    @Query(value ="SELECT * FROM Product WHERE price BETWEEN ?1 AND ?2",nativeQuery = true)
    Optional<List<Product>> findByPriceRange(Double startPrice, Double endPrice);

    Optional<List<Product>> findByProductNameLike(String productName);

    @Query(value = "SELECT * from product_tb where product_id IN (SELECT product_id from cartsummary_tb where cart_id = ?1)",nativeQuery = true)
    Optional<List<Product>> getProductsByCartId(Long cartId);

}
