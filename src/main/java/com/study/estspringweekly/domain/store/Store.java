package com.study.estspringweekly.domain.store;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Store(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.createdAt = LocalDateTime.now();
    }
}
