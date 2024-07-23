package com.study.estspringweekly.domain.order;

import com.study.estspringweekly.domain.customer.CustomerDTO;
import com.study.estspringweekly.domain.orderItem.OrderItemDTO;
import com.study.estspringweekly.domain.store.StoreDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private CustomerDTO customer;
    private StoreDTO store;
    private List<OrderItemDTO> orderItems = new ArrayList<>();

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.customer = new CustomerDTO(order.getCustomer());
        this.store = new StoreDTO(order.getStore());
        if (order.getOrderItems() != null) {
            this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
        }
    }
}
