package com.study.estspringweekly.repository;

import com.study.estspringweekly.domain.store.Store;
import com.study.estspringweekly.domain.store.StoreDTO;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByOrders_CompletedAtBetween(LocalDateTime start, LocalDateTime end);

}
