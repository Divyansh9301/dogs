package com.pets.controller;

import com.pets.dto.OrderRequest;
import com.pets.model.Order;
import com.pets.model.OrderStatus;
import com.pets.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest, Authentication authentication) {
        // Extract user ID from authentication (hardcoded for now)
        Long userId = 1L;
        Order order = orderService.createOrder(orderRequest, userId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders(Authentication authentication) {
        Long userId = 1L; // Extract from authentication
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status, Authentication authentication) {
        Long userId = 1L; // Extract from authentication
        Order order = orderService.updateOrderStatus(id, status, userId);
        return ResponseEntity.ok(order);
    }
}
