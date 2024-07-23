package com.study.estspringweekly.domain.store;

import com.study.estspringweekly.domain.menu.MenuRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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

    private int revenue;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Store(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.revenue  = 0;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStore(StoreRequest storeRequest) {
        this.name = storeRequest.getName();
        this.address = storeRequest.getAddress();
        this.contact = storeRequest.getContact();
        this.updatedAt = LocalDateTime.now();
    }

    public void addRevenue(int price) {
        this.revenue += price;
    }
}
