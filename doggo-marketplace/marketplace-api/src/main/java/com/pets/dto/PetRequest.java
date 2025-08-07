package com.pets.dto;

import com.pets.model.Gender;

public class PetRequest {
    private String name;
    private Integer fatherBreedId;
    private Integer motherBreedId;
    private Integer ageMonths;
    private Gender gender;
    private Boolean vaccinated;
    private String imgUrl;

    public PetRequest() {}

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFatherBreedId() {
        return fatherBreedId;
    }

    public void setFatherBreedId(Integer fatherBreedId) {
        this.fatherBreedId = fatherBreedId;
    }

    public Integer getMotherBreedId() {
        return motherBreedId;
    }

    public void setMotherBreedId(Integer motherBreedId) {
        this.motherBreedId = motherBreedId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
