package com.study.estspringweekly.domain.menu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    private String category;

    private int price;

    private String info;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Menu(String name, String category, int price, String info) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.info = info;
        this.createdAt = LocalDateTime.now();
    }
}
