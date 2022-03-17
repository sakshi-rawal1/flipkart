package com.sakshi.flipkart.impl;

import com.sakshi.flipkart.dto.OrderDto;
import com.sakshi.flipkart.dto.ProductDto;
import com.sakshi.flipkart.exception.ResourceNotFoundException;
import com.sakshi.flipkart.model.Cart;
import com.sakshi.flipkart.model.Customer;
import com.sakshi.flipkart.model.Order;
import com.sakshi.flipkart.model.Product;
import com.sakshi.flipkart.repository.*;
import com.sakshi.flipkart.service.OrderService;
import com.sakshi.flipkart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {
        OrderDto finalOrderDto = orderDto;
        Cart cart = cartRepository.checkActiveCart(orderDto.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","customerId", finalOrderDto.getCustomerId().toString())
        );
        Customer customer = customerRepository.findById(orderDto.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","customerId",finalOrderDto.getCustomerId().toString())
        );
        Order order = convertDtoToEntity(orderDto);
        order = orderRepository.save(order);
        orderDto = convertEntityToDto(order);
        return orderDto;
    }


    @Override
    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new ResourceNotFoundException("Order","orderId",orderId.toString())
        );
        OrderDto orderDto = convertEntityToDto(order);
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new ResourceNotFoundException("Order","orderId",orderId.toString())
        );
        OrderDto orderDto = convertEntityToDto(order);
        orderRepository.deleteById(orderId);
        return orderDto;
    }

    @Override
    public OrderDto getLastOrder(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","customerId",customerId.toString())
        );
        Order order = orderRepository.findByCustomerId(customerId).get();
        OrderDto orderDto = convertEntityToDto(order);
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrderByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","customerId",customerId.toString())
        );
        List<Order> orders = orderRepository.findByCustomerCustomerId(customerId).get();
        List<OrderDto> orderDtos = orders.stream().map(i->convertEntityToDto(i)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<ProductDto> getListOfProductsByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new ResourceNotFoundException("Order","orderId",orderId.toString())
        );
        Cart cart = cartRepository.findById(order.getCart().getCartId()).orElseThrow(
                ()-> new ResourceNotFoundException("Cart","cartId",order.getCart().getCartId().toString())
        );
        List<Product> products = productRepository.getProductsByCartId(order.getCart().getCartId()).get();
        List<ProductDto> productDtos = products.stream().map(i-> convertProductEntityToDto(i)).collect(Collectors.toList());
        return productDtos;
    }
    private ProductDto convertProductEntityToDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .productName(product.getProductName())
                .companyId(product.getCompany().getCompanyId())
                .categoryId(product.getCategory().getCategoryId())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        return productDto;
    }

    private Order convertDtoToEntity(OrderDto orderDto) {
        Order order = Order.builder()
                .timestamp(Instant.now())
                .totalPrice(orderDto.getTotalPrice())
                .cart(cartRepository.getById(orderDto.getCartId()))
                .address(addressRepository.getById(orderDto.getAddressId()))
                .customer(customerRepository.getById(orderDto.getCustomerId())).build();
        return order;
    }

    private OrderDto convertEntityToDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .addressId(order.getAddress().getAddressId())
                .customerId(order.getCustomer().getCustomerId())
                .cartId(order.getCart().getCartId())
                .timestamp(Instant.now())
                .totalPrice(order.getTotalPrice()).build();
        return orderDto;
    }
}
