package com.study.estspringweekly.domain.order;

import com.study.estspringweekly.domain.customer.Customer;
import com.study.estspringweekly.domain.orderItem.OrderItem;
import com.study.estspringweekly.domain.store.Store;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime canceledAt;

    @Builder
    public Order(Customer customer, Store store, List<OrderItem> orderItems) {
        this.customer = customer;
        setStore(store);
        setOrderItems(orderItems);
        this.orderStatus = OrderStatus.RECEIVED;
        this.totalPrice = 0;
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                this.totalPrice += orderItem.getMenu().getPrice();
            }
        }
        this.createdAt = LocalDateTime.now();
    }

    //주문 상태 변경
    public void updateToCompleted() {
        if (this.completedAt != null) {
            throw new RuntimeException("이미 완료된 주문입니다.");
        }
        if (this.canceledAt != null) {
            throw new RuntimeException("이미 취소된 주문입니다.");
        }
        this.orderStatus = OrderStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                orderItem.getMenu().increaseSalesCount();
            }
        }
    }

    public void updateToCanceled() {
        if (this.completedAt != null) {
            throw new RuntimeException("이미 완료된 주문입니다.");
        }
        if (this.canceledAt != null) {
            throw new RuntimeException("이미 취소된 주문입니다.");
        }
        this.orderStatus = OrderStatus.CANCELED;
        this.canceledAt = LocalDateTime.now();
    }

    //연관관계 메서드
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(this);
            }
        }
    }

    public void setStore(Store store) {
        this.store = store;
        store.getOrders().add(this);
    }
}
