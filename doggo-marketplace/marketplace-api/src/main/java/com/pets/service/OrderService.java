package com.pets.service;

import com.pets.dto.OrderRequest;
import com.pets.model.Order;
import com.pets.model.OrderStatus;
import com.pets.model.Pet;
import com.pets.model.User;
import com.pets.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PetService petService;
    
    @Autowired
    private UserService userService;

    public Order createOrder(OrderRequest request, Long buyerId) {
        User buyer = userService.getUserById(buyerId);
        Pet pet = petService.getPetById(request.getPetId());
        
        // Check if pet is available
        if (pet.getSoldOut()) {
            throw new RuntimeException("Pet is already sold");
        }
        
        // Calculate total amount (pet price + processing fee)
        int totalAmount = pet.getPriceCents() + 5000; // $50 processing fee
        
        Order order = new Order();
        order.setBuyer(buyer);
        order.setPet(pet);
        order.setTotalAmountCents(totalAmount);
        order.setPaymentMethod(request.getPaymentMethod());
        order.setStatus(OrderStatus.PENDING);
        
        Order savedOrder = orderRepository.save(order);
        
        // Mark pet as sold
        petService.markPetAsSold(pet.getId());
        
        return savedOrder;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByBuyer(Long buyerId) {
        User buyer = userService.getUserById(buyerId);
        return orderRepository.findByBuyer(buyer);
    }

    public List<Order> getOrdersBySeller(Long sellerId) {
        User seller = userService.getUserById(sellerId);
        return orderRepository.findByPetSeller(seller);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void processPayment(Long orderId, String transactionId) {
        Order order = getOrderById(orderId);
        order.setTransactionId(transactionId);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }
} 