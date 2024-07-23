package com.study.estspringweekly.domain.customer;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;
    private String contact;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.contact = customer.getContact();
        this.address = customer.getAddress();
        this.createdAt = customer.getCreatedAt();
        this.updatedAt = customer.getUpdatedAt();
    }
}
