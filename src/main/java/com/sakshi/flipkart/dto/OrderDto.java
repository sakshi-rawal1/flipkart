package com.sakshi.flipkart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Instant timestamp;
    private Double totalPrice;
    private Long customerId;
    private Long cartId;
    private Long addressId;
}
