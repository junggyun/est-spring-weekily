package com.study.estspringweekly.domain.customer;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private String contact;

    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Customer(String name, String contact, String address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.createdAt = LocalDateTime.now();
    }
}
