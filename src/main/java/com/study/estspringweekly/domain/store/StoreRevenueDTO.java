package com.study.estspringweekly.domain.store;

import com.study.estspringweekly.domain.order.Order;
import com.study.estspringweekly.domain.order.OrderDTO;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreRevenueDTO {

    private StoreDTO store;
    private int revenue;

    public StoreRevenueDTO(Store store, List<OrderDTO> orders) {
        this.store = new StoreDTO(store);
        if (orders != null) {
            for (OrderDTO order : orders) {
                revenue += order.getTotalPrice();
            }
        }
    }
}
