package com.pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "p_name", nullable = false)
    @NotBlank(message = "Pet name is required")
    @Size(max = 100)
    private String pName;

    @Column(name = "p_age", nullable = false)
    @NotNull(message = "Pet age is required")
    private Integer pAge;

    @Column(name = "gender", nullable = false)
    @NotBlank(message = "Gender is required")
    private String gender; // Radio Button value

    @Column(name = "p_photo", nullable = false)
    @NotBlank(message = "Pet photo is required")
    @Size(max = 255)
    private String pPhoto;

    @Column(name = "is_vaccinated", nullable = false)
    @NotNull(message = "Vaccination status is required")
    private Boolean isVaccinated; // TINYINT

    @Column(name = "fid", nullable = false)
    @NotNull(message = "Father breed is required")
    private Integer fid; // Foreign Key references breed(bid)

    @Column(name = "mid", nullable = false)
    @NotNull(message = "Mother breed is required")
    private Integer mid; // Foreign Key references breed(bid)

    @Column(name = "seller_id", nullable = false)
    @NotNull(message = "Seller is required")
    private Integer sellerId; // Foreign Key references user(userid)

    // Default constructor
    public Pet() {}

    // Constructor with all fields
    public Pet(String pName, Integer pAge, String gender, String pPhoto, 
               Boolean isVaccinated, Integer fid, Integer mid, Integer sellerId) {
        this.pName = pName;
        this.pAge = pAge;
        this.gender = gender;
        this.pPhoto = pPhoto;
        this.isVaccinated = isVaccinated;
        this.fid = fid;
        this.mid = mid;
        this.sellerId = sellerId;
    }

    // Getters and Setters
    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public Integer getPAge() {
        return pAge;
    }

    public void setPAge(Integer pAge) {
        this.pAge = pAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPPhoto() {
        return pPhoto;
    }

    public void setPPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public Boolean getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(Boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }
} 