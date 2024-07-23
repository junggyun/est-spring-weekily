package com.study.estspringweekly.domain.store;

import com.study.estspringweekly.domain.menu.MenuRequest;
import com.study.estspringweekly.domain.order.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String contact;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Store(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStore(StoreRequest storeRequest) {
        this.name = storeRequest.getName();
        this.address = storeRequest.getAddress();
        this.contact = storeRequest.getContact();
        this.updatedAt = LocalDateTime.now();
    }
}
