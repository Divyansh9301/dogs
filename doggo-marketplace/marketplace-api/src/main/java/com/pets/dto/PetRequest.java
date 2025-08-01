package com.pets.dto;

import com.pets.model.Gender;

public class PetRequest {
    private String name;
    private String breed;
    private String fatherBreed;
    private String motherBreed;
    private Integer ageMonths;
    private Gender gender;
    private Boolean vaccinated;
    private Integer priceCents;
    private String imgUrl;
    private String medicalHistory;
    private String description;
    private String location;

    public PetRequest() {}

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFatherBreed() {
        return fatherBreed;
    }

    public void setFatherBreed(String fatherBreed) {
        this.fatherBreed = fatherBreed;
    }

    public String getMotherBreed() {
        return motherBreed;
    }

    public void setMotherBreed(String motherBreed) {
        this.motherBreed = motherBreed;
    }

    public Integer getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(Integer ageMonths) {
        this.ageMonths = ageMonths;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Integer getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(Integer priceCents) {
        this.priceCents = priceCents;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
} 