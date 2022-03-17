package com.sakshi.flipkart.controller;

import com.sakshi.flipkart.dto.OrderDto;
import com.sakshi.flipkart.dto.ProductDto;
import com.sakshi.flipkart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<OrderDto>(orderService.placeOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(Long orderId){
        return new ResponseEntity<OrderDto>(orderService.getOrder(orderId),HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderDto> deleteOrder(@RequestBody Long orderId){
        return new ResponseEntity<OrderDto>(orderService.deleteOrder(orderId),HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<OrderDto> getLastOrder(@PathVariable Long customerId){
        return new ResponseEntity<OrderDto>(orderService.getLastOrder(customerId),HttpStatus.OK);
    }

    @GetMapping("/all/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrderByCustomerId(@PathVariable Long customerId){
        return new ResponseEntity<List<OrderDto>>(orderService.getOrderByCustomerId(customerId),HttpStatus.OK);
    }

    @GetMapping("/products/{orderId}")
    public ResponseEntity<List<ProductDto>> getListOfProductsByOrderId(@PathVariable Long orderId){
        return new ResponseEntity<List<ProductDto>>(orderService.getListOfProductsByOrderId(orderId),HttpStatus.OK);
    }
}
