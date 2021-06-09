package com.ttsr.springdatademo.dto;

import com.ttsr.springdatademo.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    public CategoryDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }
}
