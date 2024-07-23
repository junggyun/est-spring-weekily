package com.study.estspringweekly.service;

import com.study.estspringweekly.domain.order.OrderDTO;
import com.study.estspringweekly.domain.store.Store;
import com.study.estspringweekly.domain.store.StoreDTO;
import com.study.estspringweekly.domain.store.StoreRequest;
import com.study.estspringweekly.domain.store.StoreRevenueDTO;
import com.study.estspringweekly.domain.store.StoreRevenueRequest;
import com.study.estspringweekly.repository.OrderRepository;
import com.study.estspringweekly.repository.StoreRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, OrderRepository orderRepository) {
        this.storeRepository = storeRepository;
        this.orderRepository = orderRepository;
    }

    //매장 단건 조회
    public StoreDTO getStore(Long id) {
        Store store = storeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 매장을 찾을 수 없습니다."));

        return new StoreDTO(store);
    }

    //매장 전체 조회
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream()
            .map(StoreDTO::new)
            .collect(Collectors.toList());
    }

    public List<OrderDTO> findByCompletedAtBetweenAndStore_Id(StoreRevenueRequest request,
        Long storeId) {
        List<OrderDTO> collect = orderRepository.findByCompletedAtBetweenAndStore_Id(
                request.getStart(), request.getEnd(),
                storeId).stream()
            .map(OrderDTO::new)
            .collect(Collectors.toList());
        log.info(collect.size() + "");
        return collect;
    }

    //매장별 특정 기간 매출 조회
    public List<StoreRevenueDTO> getStoresRevenueByPeriod(StoreRevenueRequest request) {
        return storeRepository.findAll().stream()
            .map(store -> new StoreRevenueDTO(store,
                findByCompletedAtBetweenAndStore_Id(request, store.getId())))
            .collect(Collectors.toList());
    }

    //매장 등록
    @Transactional
    public StoreDTO createStore(StoreRequest storeRequest) {
        Store store = Store.builder()
            .name(storeRequest.getName())
            .address(storeRequest.getAddress())
            .contact(storeRequest.getContact())
            .build();
        storeRepository.save(store);

        return new StoreDTO(store);
    }

    //매장 수정
    @Transactional
    public StoreDTO updateStore(Long id, StoreRequest storeRequest) {
        Store store = storeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 매장을 찾을 수 없습니다."));
        store.updateStore(storeRequest);

        return new StoreDTO(store);
    }

    //매장 삭제
    @Transactional
    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 매장을 찾을 수 없습니다."));
        storeRepository.delete(store);
    }
}
