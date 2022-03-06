package com.sakshi.flipkart.repository;

import com.sakshi.flipkart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {

    Optional<List<Image>> findByProductProductId(Long productId);
}
