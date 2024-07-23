package com.study.estspringweekly.repository;

import com.study.estspringweekly.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
