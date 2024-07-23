package com.study.estspringweekly.domain.menu;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuDTO {

    private Long id;
    private String name;
    private Category category;
    private int price;
    private String info;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MenuDTO(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.category = menu.getCategory();
        this.price = menu.getPrice();
        this.info = menu.getInfo();
        this.createdAt = menu.getCreatedAt();
        this.updatedAt = menu.getUpdatedAt();
    }
}
