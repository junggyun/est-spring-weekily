package com.study.estspringweekly.service;

import com.study.estspringweekly.domain.customer.Customer;
import com.study.estspringweekly.domain.customer.CustomerDTO;
import com.study.estspringweekly.domain.customer.CustomerRequest;
import com.study.estspringweekly.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //고객 단건 조회
    public CustomerDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 고객을 찾을 수 없습니다."));

        return new CustomerDTO(customer);
    }

    //고객 전체 조회
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
            .map(CustomerDTO::new)
            .collect(Collectors.toList());
    }

    //고객 등록
    @Transactional
    public CustomerDTO createCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
            .name(customerRequest.getName())
            .contact(customerRequest.getContact())
            .address(customerRequest.getAddress())
            .build();
        customerRepository.save(customer);

        return new CustomerDTO(customer);
    }

    //고객 수정
    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 고객을 찾을 수 없습니다."));
        customer.updateCustomer(customerRequest);

        return new CustomerDTO(customer);
    }

    //고객 삭제
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 고객을 찾을 수 없습니다."));
        customerRepository.delete(customer);
    }
}
