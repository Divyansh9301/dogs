package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "State")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stid")
    private Integer stid;

    @Column(name = "stname", nullable = false, unique = true)
    @NotBlank(message = "State name is required")
    @Size(max = 100)
    private String stname;

    // Default constructor
    public State() {}

    // Constructor
    public State(String stname) {
        this.stname = stname;
    }

    // Getters and Setters
    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }
} 