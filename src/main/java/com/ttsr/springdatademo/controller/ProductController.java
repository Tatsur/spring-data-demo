package com.ttsr.springdatademo.controller;

import com.ttsr.springdatademo.model.Product;
import com.ttsr.springdatademo.repository.ProductRepository;
import com.ttsr.springdatademo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam Map<String,String> params){
        return productService.findAll(params);
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id);
    }
    @PostMapping
    public  Product save(@RequestBody Product product){
        return productService.save(product);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/search_by_price_greater")
    public List<Product> findAllByPriceGreaterThan(BigDecimal price){
        return productService.findAllByPriceGreaterThan(price);
    }

    @GetMapping("/search_by_price_less")
    public List<Product> findAllByPriceLessThan(BigDecimal price){
        return productService.findAllByPriceLessThan(price);
    }

    @GetMapping("/search_by_price_between")
    public List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max){
        return productService.findAllByPriceBetween(min, max);
    }
}
