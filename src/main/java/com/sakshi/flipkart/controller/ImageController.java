package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.ImageDto;
import com.sakshi.flipkart.model.Image;
import com.sakshi.flipkart.repository.ImageRepository;
import com.sakshi.flipkart.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/")
    public ResponseEntity<ImageDto> addImage(@RequestBody ImageDto imageDto){
        return new ResponseEntity<ImageDto>(imageService.addImage(imageDto), HttpStatus.CREATED);
    }
    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDto> getImage(@PathVariable Long imageId){
        return new ResponseEntity<ImageDto>(imageService.getImage(imageId),HttpStatus.OK);
    }
    @GetMapping("/all/{productId}")
    public ResponseEntity<List<ImageDto>> getImagesByProductId(@PathVariable Long productId){
        return new ResponseEntity<List<ImageDto>>(imageService.getImagesByProductId(productId),HttpStatus.OK);
    }
    @DeleteMapping("/{imageId}")
    public ResponseEntity<ImageDto> deleteImage(@PathVariable Long imageId){
        return new ResponseEntity<ImageDto>(imageService.deleteImage(imageId),HttpStatus.OK);
    }
}
