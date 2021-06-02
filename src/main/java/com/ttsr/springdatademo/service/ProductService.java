package com.ttsr.springdatademo.service;

import com.ttsr.springdatademo.dto.ProductDto;
import com.ttsr.springdatademo.model.Category;
import com.ttsr.springdatademo.model.Product;
import com.ttsr.springdatademo.repository.ProductRepository;
import com.ttsr.springdatademo.service.spec.ProductSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public Page<ProductDto> findAll(Map<String, String> params, Integer pageNumber, Integer pageSize){
        final Specification<Product> specification = build(params);
        Page<Product> productPage = productRepository.findAll(specification, PageRequest.of(pageNumber-1,pageSize));
        return new PageImpl<>(productPage.getContent().stream().map(ProductDto::new)
            .collect(Collectors.toList()),productPage.getPageable(),productPage.getTotalElements());
    }

    public Optional<ProductDto> findById(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(()->new NoSuchElementException(String.format("Product with id = %s was not found",id)));
        return Optional.of(new ProductDto(product));
    }

    public ProductDto save(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        Category category = categoryService.findByName(productDto.getCategory())
                .orElseThrow(()->new NoSuchElementException(String.format("'%s' category doesn't exist",productDto.getCategory())));
        product.setCategory(category);
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public static Specification<Product> build(Map<String, String> params){
        return params.entrySet().stream()
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
                    if("category".equals(it.getKey())){
                        return ProductSpec.categoryNameEq(it.getValue());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse(null);
    }
}
