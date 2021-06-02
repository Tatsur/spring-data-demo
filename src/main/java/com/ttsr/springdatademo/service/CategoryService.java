package com.ttsr.springdatademo.service;

import com.ttsr.springdatademo.dto.CategoryDto;
import com.ttsr.springdatademo.model.Category;
import com.ttsr.springdatademo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private CategoryDto selectedCategory;

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByName(String name){
        return categoryRepository.findByName(name);
    }

    public List<CategoryDto> findAll(){
        return categoryRepository.findAll()
                .stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    public CategoryDto getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryDto selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
}
