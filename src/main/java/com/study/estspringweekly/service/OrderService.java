package com.study.estspringweekly.service;

import com.study.estspringweekly.domain.customer.Customer;
import com.study.estspringweekly.domain.menu.Menu;
import com.study.estspringweekly.domain.menu.MenuRequest;
import com.study.estspringweekly.domain.order.Order;
import com.study.estspringweekly.domain.order.OrderDTO;
import com.study.estspringweekly.domain.order.OrderRequest;
import com.study.estspringweekly.domain.orderItem.OrderItem;
import com.study.estspringweekly.domain.store.Store;
import com.study.estspringweekly.repository.CustomerRepository;
import com.study.estspringweekly.repository.MenuRepository;
import com.study.estspringweekly.repository.OrderItemRepository;
import com.study.estspringweekly.repository.OrderRepository;
import com.study.estspringweekly.repository.StoreRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
        StoreRepository storeRepository, MenuRepository menuRepository,
        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.menuRepository = menuRepository;
        this.orderItemRepository = orderItemRepository;
    }

    //주문 단건 조회
    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));

        return new OrderDTO(order);
    }

    //주문 전체 조회
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
            .map(OrderDTO::new)
            .collect(Collectors.toList());
    }

    //주문 등록
    @Transactional
    public OrderDTO createOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
            .orElseThrow(() -> new IllegalArgumentException("해당 고객을 찾을 수 없습니다."));
        Store store = storeRepository.findById(orderRequest.getStoreId())
            .orElseThrow(() -> new IllegalArgumentException("해당 매장을 찾을 수 없습니다."));
        List<OrderItem> orderItems = new ArrayList<>();
        if (orderRequest.getMenuIds() != null) {
            for (Long menuId : orderRequest.getMenuIds()) {
                Menu menu = menuRepository.findById(menuId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));
                OrderItem orderItem = OrderItem.builder()
                    .menu(menu)
                    .build();
                orderItems.add(orderItem);
            }
        }

        Order order = Order.builder()
            .customer(customer)
            .store(store)
            .orderItems(orderItems)
            .build();
        orderRepository.save(order);

        return new OrderDTO(order);
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));
        order.updateToCanceled();
    }

    //주문 완료
    @Transactional
    public void completeOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));
        order.updateToCompleted();
    }
}
