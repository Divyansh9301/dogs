package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "BookPet")
public class BookPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bk_id")
    private Integer bkId;

    @Column(name = "p_id", nullable = false)
    @NotNull(message = "Pet is required")
    private Integer pId; // Foreign Key references Pet(p_id)

    @Column(name = "Price", nullable = false)
    @NotNull(message = "Price is required")
    private Float price;

    @Column(name = "date", nullable = false)
    @NotNull(message = "Date is required")
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @NotNull(message = "Time is required")
    private LocalTime time;

    @Column(name = "buyer_id", nullable = false)
    @NotNull(message = "Buyer is required")
    private Integer buyerId; // Foreign Key references user(userid)

    // Default constructor
    public BookPet() {}

    // Constructor with all fields
    public BookPet(Integer pId, Float price, LocalDate date, LocalTime time, Integer buyerId) {
        this.pId = pId;
        this.price = price;
        this.date = date;
        this.time = time;
        this.buyerId = buyerId;
    }

    // Getters and Setters
    public Integer getBkId() {
        return bkId;
    }

    public void setBkId(Integer bkId) {
        this.bkId = bkId;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }
} 