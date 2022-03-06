package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.ImageDto;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Image;
import com.sakshi.flipkart.model.Product;
import com.sakshi.flipkart.repository.ImageRepository;
import com.sakshi.flipkart.repository.ProductRepository;
import com.sakshi.flipkart.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageDto addImage(ImageDto imageDto) {
        ImageDto finalImageDto = imageDto;
        Product product = productRepository.findById(imageDto.getProductId()).orElseThrow(
                ()-> new ResourceNotFoundException("Product","productId", finalImageDto.getProductId().toString())
        );
        Image image = convertDtoToEntity(imageDto);
        image = imageRepository.save(image);
        imageDto = convertEntityToDto(image);
        return imageDto;
    }

    private ImageDto convertEntityToDto(Image image) {
        ImageDto imageDto = ImageDto.builder()
                .imageUrl(image.getImageUrl())
                .productId(image.getProduct().getProductId())
                .build();
        return imageDto;
    }

    private Image convertDtoToEntity(ImageDto imageDto) {
        Image image = Image.builder()
                .imageUrl(imageDto.getImageUrl())
                .product(productRepository.findById(imageDto.getProductId()).get())
                .build();
        return image;
    }

    @Override
    public ImageDto deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(
                ()-> new ResourceNotFoundException("Image","imageId",imageId.toString())
        );
        ImageDto imageDto = convertEntityToDto(image);
        imageRepository.deleteById(imageId);
        return imageDto;
    }

    @Override
    public List<ImageDto> getImagesByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException("Product","productId",productId.toString())
        );
        List<Image> images = imageRepository.findByProductProductId(productId).get();
        List<ImageDto> imageDtos = images.stream().map(i->convertEntityToDto(i)).collect(Collectors.toList());
        return imageDtos;
    }

    @Override
    public ImageDto getImage(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(
                ()-> new ResourceNotFoundException("Image","imageId",imageId.toString())
        );
        ImageDto imageDto = convertEntityToDto(image);
        return imageDto;
    }
}
