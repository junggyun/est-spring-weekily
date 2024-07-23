package com.study.estspringweekly.repository;

import com.study.estspringweekly.domain.order.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByCompletedAtBetweenAndStore_Id(LocalDateTime start, LocalDateTime end,
        Long storeId);
}
