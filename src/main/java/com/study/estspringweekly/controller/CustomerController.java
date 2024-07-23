package com.study.estspringweekly.controller;

import com.study.estspringweekly.domain.customer.CustomerDTO;
import com.study.estspringweekly.domain.customer.CustomerRequest;
import com.study.estspringweekly.service.CustomerService;
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
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //고객 전체 조회
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    //고객 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    //고객 등록
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(
        @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    //고객 수정
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
        @PathVariable("id") Long id,
        @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    //고객 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
        @PathVariable("id") Long id
    ) {
        customerService.deleteCustomer(id);

        return ResponseEntity.ok("DELETE SUCCESS!");
    }
}
