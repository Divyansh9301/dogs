package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Integer roleid;

    @Column(name = "rolename", nullable = false, unique = true)
    @NotBlank(message = "Role name is required")
    @Size(max = 20)
    private String rolename;

    // Default constructor
    public Role() {}

    // Constructor
    public Role(String rolename) {
        this.rolename = rolename;
    }

    // Getters and Setters
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
} 