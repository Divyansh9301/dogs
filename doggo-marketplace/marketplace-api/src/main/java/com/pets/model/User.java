package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userid;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @Column(name = "email_id", nullable = false, unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    private String emailId;

    @Column(name = "contact_no", nullable = false, unique = true)
    @NotBlank(message = "Contact number is required")
    @Size(max = 50)
    private String contactNo;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address is required")
    @Size(max = 50)
    private String address;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    @Size(max = 50)
    private String password;

    @Column(name = "aadhar_no", nullable = false)
    @NotNull(message = "Aadhar number is required")
    private Long aadharNo;

    @Column(name = "cityid", nullable = false)
    @NotNull(message = "City is required")
    private Integer cityid;

    @Column(name = "roleid", nullable = false)
    @NotNull(message = "Role is required")
    private Integer roleid;

    // Default constructor
    public User() {}

    // Constructor with all fields
    public User(String username, String emailId, String contactNo, String address, 
                String password, Long aadharNo, Integer cityid, Integer roleid) {
        this.username = username;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.address = address;
        this.password = password;
        this.aadharNo = aadharNo;
        this.cityid = cityid;
        this.roleid = roleid;
    }

    // Getters and Setters
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
} 