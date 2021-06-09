package com.ttsr.springdatademo.component;

import com.ttsr.springdatademo.dto.OrderItemDto;
import com.ttsr.springdatademo.dto.ProductDto;
import com.ttsr.springdatademo.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.math.BigDecimal;
import java.util.*;

@Component
@SessionScope
public class Cart {

    private final ProductService productService;

    private final List<OrderItemDto> items;

    private BigDecimal totalPrice;

    private int itemsCount;

    public Cart(ProductService productService) {
        this.productService = productService;
        this.items = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
        this.itemsCount = 0;
    }

    public void add(Long productId) {
        ProductDto productDto = productService.findById(productId);
        OrderItemDto orderItem = items.stream().filter(it -> it.getProductId().equals(productId))
                .findFirst()
                .orElseGet(()-> {
                    ProductDto product = productService.findById(productId);
                    OrderItemDto orderItemDto = new OrderItemDto(productDto.getId(), productDto.getName(), productDto.getPrice(), 0);
                    items.add(orderItemDto);
                    return orderItemDto;
                        });
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        totalPrice = calcTotalPrice();
        itemsCount++;
    }

    private BigDecimal calcTotalPrice() {
        return items.stream().map(it ->  it.getProductPrice().multiply(new BigDecimal(it.getQuantity())))
                .reduce(BigDecimal::add).orElseThrow();
    }

    public List<OrderItemDto> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void remove(Long productId) {
        Optional<OrderItemDto> orderItemDto = items.stream().filter(it -> it.getProductId().equals(productId))
                .findFirst();
        if(orderItemDto.isPresent()){
            OrderItemDto orderItem = orderItemDto.get();
            orderItem.setQuantity(orderItem.getQuantity() - 1);
            if(orderItem.getQuantity() <= 0){
                items.remove(orderItem);
                return;
            }
            totalPrice = calcTotalPrice();
            itemsCount--;
        }
    }

    public void removeAll() {
        items.clear();
        itemsCount = 0;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
