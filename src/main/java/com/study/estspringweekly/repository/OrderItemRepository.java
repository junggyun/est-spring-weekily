package com.study.estspringweekly.repository;

import com.study.estspringweekly.domain.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
