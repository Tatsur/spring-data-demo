package com.ttsr.springdatademo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class OrderItemDto {

    private Long productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer quantity;

    public OrderItemDto(OrderItemDto orderItemDto) {
        productId = orderItemDto.productId;
        productName = orderItemDto.productName;
        productPrice = orderItemDto.productPrice;
        quantity = orderItemDto.quantity;
    }
}
