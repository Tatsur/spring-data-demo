package com.ttsr.springdatademo.service;

import com.ttsr.springdatademo.model.Product;
import com.ttsr.springdatademo.repository.ProductRepository;
import com.ttsr.springdatademo.service.spec.ProductSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll(Map<String, String> params){
        final Specification<Product> specification = params.entrySet().stream()
                .filter(it-> StringUtils.hasText(it.getValue()))
                .map(it->{
                    if("name".equals(it.getKey())){
                        return ProductSpec.nameLike(it.getValue());
                    }
                    if("greater".equals(it.getKey())){
                        return ProductSpec.priceGreaterThen(Integer.parseInt(it.getValue()));
                    }
                    if("less".equals(it.getKey())){
                        return ProductSpec.priceLessThen(Integer.parseInt(it.getValue()));
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse(null);
        return productRepository.findAll(specification);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByPriceGreaterThan(BigDecimal price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

    public List<Product> findAllByPriceLessThan(BigDecimal price) {
        return productRepository.findAllByPriceLessThan(price);
    }

    public List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max) {
        return productRepository.findAllByPriceBetween(min,max);
    }
}
