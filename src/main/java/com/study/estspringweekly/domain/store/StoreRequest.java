package com.study.estspringweekly.domain.store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreRequest {

    private String name;
    private String address;
    private String contact;

    @Builder
    public StoreRequest(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
}
