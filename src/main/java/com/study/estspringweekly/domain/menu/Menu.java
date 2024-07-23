package com.study.estspringweekly.domain.menu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int price;

    private String info;

    private int salesCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Menu(String name, Category category, int price, String info) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.info = info;
        this.salesCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    //수정 메서드
    public void updateMenu(MenuRequest menuRequest) {
        this.name = menuRequest.getName();
        this.category = menuRequest.getCategory();
        this.price = menuRequest.getPrice();
        this.info = menuRequest.getInfo();
        this.updatedAt = LocalDateTime.now();
    }

    public void increaseSalesCount() {
        this.salesCount += 1;
    }
}
