package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid")
    private Integer bid;

    @Column(name = "bname", nullable = false)
    @NotBlank(message = "Breed name is required")
    @Size(max = 100)
    private String bname;

    // Default constructor
    public Breed() {}

    // Constructor
    public Breed(String bname) {
        this.bname = bname;
    }

    // Getters and Setters
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
} 