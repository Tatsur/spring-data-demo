package com.ttsr.springdatademo.dto;

import com.ttsr.springdatademo.model.Category;
import com.ttsr.springdatademo.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Component
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String category;

    private BigDecimal price;

    public ProductDto(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory().getName();
        this.price = product.getPrice();
    }

    public ProductDto(ProductDto productDto){
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.category = productDto.getCategory();
        this.price = productDto.getPrice();
    }
}
