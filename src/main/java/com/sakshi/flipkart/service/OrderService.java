package com.sakshi.flipkart.service;

import com.sakshi.flipkart.dto.OrderDto;
import com.sakshi.flipkart.dto.ProductDto;

import java.util.List;

public interface OrderService {

    OrderDto placeOrder(OrderDto orderDto);

    OrderDto getOrder(Long orderId);

    OrderDto deleteOrder(Long orderId);

    OrderDto getLastOrder(Long customerId);

    List<OrderDto> getOrderByCustomerId(Long customerId);

    List<ProductDto> getListOfProductsByOrderId(Long orderId);
}
