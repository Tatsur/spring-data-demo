package com.ttsr.springdatademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ttsr.springdatademo.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findByName(String name);

    List<Product> findAllByPriceGreaterThan(BigDecimal min);

    List<Product> findAllByPriceLessThan(BigDecimal max);

    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max);
}
