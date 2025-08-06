package com.pets.dto;

import jakarta.validation.constraints.*;

public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    private String emailId;

    @NotBlank(message = "Contact number is required")
    @Size(max = 50)
    private String contactNo;

    @NotBlank(message = "Address is required")
    @Size(max = 50)
    private String address;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotNull(message = "Aadhar number is required")
    private Long aadharNo;

    @NotNull(message = "City is required")
    private Integer cityid;

    private Integer roleid = 2; // Default to regular user role (assuming 1=admin, 2=user)

    // Default constructor
    public RegisterRequest() {}

    // Constructor with all fields
    public RegisterRequest(String username, String emailId, String contactNo, String address, 
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