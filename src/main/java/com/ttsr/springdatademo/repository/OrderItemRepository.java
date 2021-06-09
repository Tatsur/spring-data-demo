package com.ttsr.springdatademo.repository;

import com.ttsr.springdatademo.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
