package com.study.estspringweekly.repository;

import com.study.estspringweekly.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
