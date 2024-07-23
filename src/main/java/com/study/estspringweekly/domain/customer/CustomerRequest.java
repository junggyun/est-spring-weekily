package com.study.estspringweekly.domain.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerRequest {

    private String name;
    private String contact;
    private String address;

    @Builder
    public CustomerRequest(String name, String contact, String address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }
}
