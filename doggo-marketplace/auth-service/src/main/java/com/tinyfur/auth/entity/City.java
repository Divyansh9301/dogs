package com.tinyfur.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityid")
    private Integer cityId;

    @Column(name = "cityname", nullable = false, unique = true, length = 50)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "stid", nullable = false)
    private State state;

    public City() {}

    public City(String cityName, State state) {
        this.cityName = cityName;
        this.state = state;
    }

    // Getters and Setters
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
} 