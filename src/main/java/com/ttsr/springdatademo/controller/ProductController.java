package com.ttsr.springdatademo.controller;

import com.ttsr.springdatademo.model.Product;
import com.ttsr.springdatademo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<Product> findAll(@RequestParam Map<String,String> params,
                                 @RequestParam(name = "page-number",defaultValue = "1") Integer pageNumber,
                                 @RequestParam(name = "page-size", defaultValue = "3") Integer pageSize){
        return productService.findAll(params, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping
    public  Product update(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
