package com.study.estspringweekly.repository;

import com.study.estspringweekly.domain.menu.Category;
import com.study.estspringweekly.domain.menu.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByCategory(Category category);

    List<Menu> findTop3ByOrderBySalesCountDescPriceDesc();

}
