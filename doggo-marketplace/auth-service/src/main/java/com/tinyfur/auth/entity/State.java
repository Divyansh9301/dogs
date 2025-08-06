package com.tinyfur.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "State")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stid")
    private Integer stateId;

    @Column(name = "stname", nullable = false, unique = true, length = 100)
    private String stateName;

    public State() {}

    public State(String stateName) {
        this.stateName = stateName;
    }

    // Getters and Setters
    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
} 