package com.pets.service;

import com.pets.dto.PetRequest;
import com.pets.model.Pet;
import com.pets.model.User;
import com.pets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    private UserService userService;

    public List<Pet> getAllPets() {
        return petRepository.findBySoldOutFalse();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public Pet createPet(PetRequest request, Long sellerId) {
        User seller = userService.getUserById(sellerId);
        
        Pet pet = new Pet();
        pet.setName(request.getName());
        pet.setBreed(request.getBreed());
        pet.setFatherBreed(request.getFatherBreed());
        pet.setMotherBreed(request.getMotherBreed());
        pet.setAgeMonths(request.getAgeMonths());
        pet.setGender(request.getGender());
        pet.setVaccinated(request.getVaccinated());
        pet.setPriceCents(request.getPriceCents());
        pet.setImgUrl(request.getImgUrl());
        pet.setMedicalHistory(request.getMedicalHistory());
        pet.setDescription(request.getDescription());
        pet.setLocation(request.getLocation());
        pet.setSeller(seller);
        pet.setSoldOut(false);

        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, PetRequest request, Long sellerId) {
        Pet pet = getPetById(id);
        
        // Check if the user is the seller
        if (pet.getSeller() == null || !pet.getSeller().getId().equals(sellerId)) {
            throw new RuntimeException("Not authorized to update this pet");
        }

        pet.setName(request.getName());
        pet.setBreed(request.getBreed());
        pet.setFatherBreed(request.getFatherBreed());
        pet.setMotherBreed(request.getMotherBreed());
        pet.setAgeMonths(request.getAgeMonths());
        pet.setGender(request.getGender());
        pet.setVaccinated(request.getVaccinated());
        pet.setPriceCents(request.getPriceCents());
        pet.setImgUrl(request.getImgUrl());
        pet.setMedicalHistory(request.getMedicalHistory());
        pet.setDescription(request.getDescription());
        pet.setLocation(request.getLocation());

        return petRepository.save(pet);
    }

    public Pet markPetAsSold(Long petId) {
        Pet pet = getPetById(petId);
        pet.setSoldOut(true);
        return petRepository.save(pet);
    }

    public void deletePet(Long id, Long sellerId) {
        Pet pet = getPetById(id);
        
        // Check if the user is the seller
        if (pet.getSeller() == null || !pet.getSeller().getId().equals(sellerId)) {
            throw new RuntimeException("Not authorized to delete this pet");
        }

        petRepository.delete(pet);
    }

    public List<Pet> searchPets(String searchTerm) {
        return petRepository.searchPets(searchTerm);
    }

    public List<Pet> getPetsByBreed(String breed) {
        return petRepository.findByBreedContainingIgnoreCase(breed);
    }

    public List<Pet> getPetsBySeller(Long sellerId) {
        User seller = userService.getUserById(sellerId);
        return petRepository.findBySeller(seller);
    }
} 