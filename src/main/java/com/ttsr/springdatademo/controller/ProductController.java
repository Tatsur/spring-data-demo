package com.ttsr.springdatademo.controller;

import com.ttsr.springdatademo.model.Product;
import com.ttsr.springdatademo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).get();
    }
    @PostMapping
    public  Product save(@RequestBody Product product){
        return productRepository.save(product);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id){
        productRepository.deleteById(id);
    }

    @GetMapping("/search_by_price_greater")
    public List<Product> findAllByPriceGreaterThan(BigDecimal price){
        return productRepository.findAllByPriceGreaterThan(price);
    }

    @GetMapping("/search_by_price_less")
    public List<Product> findAllByPriceLessThan(BigDecimal price){
        return productRepository.findAllByPriceLessThan(price);
    }

    @GetMapping("/search_by_price_between")
    public List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max){
        return productRepository.findAllByPriceBetween(min, max);
    }
}
