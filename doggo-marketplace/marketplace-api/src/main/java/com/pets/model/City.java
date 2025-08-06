package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityid")
    private Integer cityid;

    @Column(name = "cityname", nullable = false, unique = true)
    @NotBlank(message = "City name is required")
    @Size(max = 50)
    private String cityname;

    @Column(name = "stid", nullable = false)
    @NotNull(message = "State is required")
    private Integer stid;

    // Default constructor
    public City() {}

    // Constructor
    public City(String cityname, Integer stid) {
        this.cityname = cityname;
        this.stid = stid;
    }

    // Getters and Setters
    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }
} 