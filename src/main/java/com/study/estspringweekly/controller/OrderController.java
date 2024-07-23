package com.study.estspringweekly.controller;

import com.study.estspringweekly.domain.order.OrderDTO;
import com.study.estspringweekly.domain.order.OrderRequest;
import com.study.estspringweekly.service.OrderService;
import java.util.List;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //주문 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    //주문 전체 조회
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    //주문 생성
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(
        @RequestBody OrderRequest orderRequest
    ) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    //주문 취소
    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelOrder(
        @PathVariable("id") Long id
    ) {
        orderService.cancelOrder(id);

        return ResponseEntity.ok("CANCEL SUCCESS!");
    }

    //주문 완료
    @PostMapping("/{id}/complete")
    public ResponseEntity<String> completeOrder(
        @PathVariable("id") Long id
    ) {
        orderService.completeOrder(id);

        return ResponseEntity.ok("COMPLETE SUCCESS!");
    }
}
