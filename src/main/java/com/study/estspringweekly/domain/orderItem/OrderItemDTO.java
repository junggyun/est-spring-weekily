package com.study.estspringweekly.domain.orderItem;

import com.study.estspringweekly.domain.menu.Menu;
import com.study.estspringweekly.domain.menu.MenuDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;
    private MenuDTO menu;

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.menu = new MenuDTO(orderItem.getMenu());
    }
}
