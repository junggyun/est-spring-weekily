package com.study.estspringweekly.domain.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuRequest {

    private String name;
    private Category category;
    private int price;
    private String info;

    @Builder
    public MenuRequest(String name, Category category, int price, String info) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.info = info;
    }
}
