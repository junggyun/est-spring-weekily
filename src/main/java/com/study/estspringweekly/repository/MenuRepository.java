package com.study.estspringweekly.repository;

import com.study.estspringweekly.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
