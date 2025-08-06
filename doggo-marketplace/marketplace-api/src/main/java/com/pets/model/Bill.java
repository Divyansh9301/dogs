package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blid")
    private Integer blid;

    @Column(name = "useracc", nullable = false)
    @NotNull(message = "User account is required")
    private Long useracc;

    @Column(name = "Amt", nullable = false)
    @NotNull(message = "Amount is required")
    private Float amt;

    @Column(name = "date", nullable = false)
    @NotNull(message = "Date is required")
    private LocalDate date;

    @Column(name = "bk_id", nullable = false)
    @NotNull(message = "Booking is required")
    private Integer bkId; // Foreign Key references BookPet(bk_id)

    // Default constructor
    public Bill() {}

    // Constructor with all fields
    public Bill(Long useracc, Float amt, LocalDate date, Integer bkId) {
        this.useracc = useracc;
        this.amt = amt;
        this.date = date;
        this.bkId = bkId;
    }

    // Getters and Setters
    public Integer getBlid() {
        return blid;
    }

    public void setBlid(Integer blid) {
        this.blid = blid;
    }

    public Long getUseracc() {
        return useracc;
    }

    public void setUseracc(Long useracc) {
        this.useracc = useracc;
    }

    public Float getAmt() {
        return amt;
    }

    public void setAmt(Float amt) {
        this.amt = amt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getBkId() {
        return bkId;
    }

    public void setBkId(Integer bkId) {
        this.bkId = bkId;
    }
} 