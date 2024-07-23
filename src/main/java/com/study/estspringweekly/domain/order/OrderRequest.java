package com.study.estspringweekly.domain.order;

import com.study.estspringweekly.domain.menu.MenuRequest;
import com.study.estspringweekly.domain.orderItem.OrderItem;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequest {

    private Long customerId;
    private Long storeId;
    private List<Long> menuIds = new ArrayList<>();

    @Builder
    public OrderRequest(Long customerId, Long storeId, List<Long> menuIds) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.menuIds = menuIds;
    }
}
