package com.study.estspringweekly.controller;

import com.study.estspringweekly.domain.store.StoreDTO;
import com.study.estspringweekly.domain.store.StoreRequest;
import com.study.estspringweekly.service.StoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    //매장 전체 조회
    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    //매장 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStore(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(storeService.getStore(id));
    }

    //매장 등록
    @PostMapping
    public ResponseEntity<StoreDTO> createStore(
        @RequestBody StoreRequest storeRequest
    ) {
        return ResponseEntity.ok(storeService.createStore(storeRequest));
    }

    //매장 수정
    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(
        @PathVariable("id") Long id,
        @RequestBody StoreRequest storeRequest
    ) {
        return ResponseEntity.ok(storeService.updateStore(id, storeRequest));
    }

    //매장 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(
        @PathVariable("id") Long id
    ) {
        storeService.deleteStore(id);

        return ResponseEntity.ok("DELETE SUCCESS!");
    }
}
