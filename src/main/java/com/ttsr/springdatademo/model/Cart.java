package com.ttsr.springdatademo.model;

import com.ttsr.springdatademo.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Cart {

    private List<ProductDto> items;

    private BigDecimal totalPrice;

    private int itemsCount;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void add(ProductDto product){
        items.add(product);
        totalPrice = totalPrice.add(product.getPrice());
        itemsCount++;
    }

    public List<ProductDto> getItems(){
        return Collections.unmodifiableList(items);
    }

    public void remove(Long id){
        Optional<ProductDto> product = items.stream()
                .filter(it->it.getId().equals(id))
                .findAny();
        product.ifPresent(it-> items.remove(it));
        totalPrice = totalPrice.subtract(product.get().getPrice());
        itemsCount--;
    }

    public void removeAll(){
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
