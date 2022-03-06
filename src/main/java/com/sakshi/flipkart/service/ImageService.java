package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.ImageDto;

import java.util.List;

public interface ImageService {
    ImageDto addImage(ImageDto imageDto);
    ImageDto deleteImage(Long imageId);
    List<ImageDto> getImagesByProductId(Long productId);
    ImageDto getImage(Long imageId);
}
