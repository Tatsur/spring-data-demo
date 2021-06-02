package com.ttsr.springdatademo.controller;

import com.ttsr.springdatademo.dto.CategoryDto;
import com.ttsr.springdatademo.dto.ProductDto;
import com.ttsr.springdatademo.model.Product;
import com.ttsr.springdatademo.service.CategoryService;
import com.ttsr.springdatademo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam Map<String,String> params,
                                    @RequestParam(name = "page-number",defaultValue = "1") Integer pageNumber,
                                    @RequestParam(name = "page-size", defaultValue = "3") Integer pageSize){
        return productService.findAll(params, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(()-> new NoSuchElementException("Product with that id doesn't exist"));
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  ProductDto create(@RequestBody ProductDto product){
        return productService.save(product);
    }

    @PutMapping
    public  ProductDto update(@RequestBody ProductDto product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
