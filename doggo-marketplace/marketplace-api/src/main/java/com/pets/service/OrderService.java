package com.pets.service;

import com.pets.dto.OrderRequest;
import com.pets.model.Order;
import com.pets.model.Pet;
import com.pets.model.User;
import com.pets.model.OrderStatus;
import com.pets.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PetService petService;
    
    @Autowired
    private UserService userService;

    public Order createOrder(OrderRequest request, Long userId) {
        Pet pet = petService.getPetById(request.getPetId());
        User buyer = userService.getUserById(userId);
        
        Order order = new Order();
        order.setPet(pet);
        order.setBuyer(buyer);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        User user = userService.getUserById(userId);
        return orderRepository.findByBuyer(user);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status, Long userId) {
        Order order = getOrderById(orderId);
        
        // Check if user is buyer or seller
        if (!order.getBuyer().getUserid().equals(userId) && 
            !order.getPet().getSellerId().equals(userId.intValue())) {
            throw new RuntimeException("Not authorized to update this order");
        }
        
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
