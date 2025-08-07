package com.pets.dto;

public class OrderRequest {
    private Integer petId;
    private String paymentMethod;

    public OrderRequest() {}

    public OrderRequest(Integer petId, String paymentMethod) {
        this.petId = petId;
        this.paymentMethod = paymentMethod;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
