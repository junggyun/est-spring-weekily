package com.study.estspringweekly.domain.store;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreDTO {

    private Long id;
    private String name;
    private String address;
    private String contact;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StoreDTO(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.address = store.getAddress();
        this.contact = store.getContact();
        this.createdAt = store.getCreatedAt();
        this.updatedAt = store.getUpdatedAt();
    }
}
