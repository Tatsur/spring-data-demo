package com.ttsr.springdatademo.dto;

import com.ttsr.springdatademo.component.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
@NoArgsConstructor
public class CartDto {

    private List<OrderItemDto> items;

    private BigDecimal totalPrice;

    private int itemsCount;

    public CartDto(Cart cart){
        items = new ArrayList<>();
        items.addAll(cart.getItems().stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList()));
        totalPrice = cart.getTotalPrice();
        itemsCount = cart.getItemsCount();
    }

    public List<OrderItemDto> showItems(){
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getItemsCount() {
        return itemsCount;
    }
}
