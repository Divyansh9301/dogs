package com.pets.dto;

public class OrderRequest {
    private Long petId;
    private String paymentMethod;

    public OrderRequest() {}

    public OrderRequest(Long petId, String paymentMethod) {
        this.petId = petId;
        this.paymentMethod = paymentMethod;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
} 