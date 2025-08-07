package com.pets.service;

import com.pets.dto.PetRequest;
import com.pets.model.Pet;
import com.pets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    private UserService userService;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Integer id) {
        return petRepository.findByPId(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public Pet createPet(PetRequest request, Integer sellerId) {
        Pet pet = new Pet();
        pet.setPName(request.getName());
        pet.setPAge(request.getAgeMonths());
        pet.setGender(request.getGender().toString());
        pet.setPPhoto(request.getImgUrl());
        pet.setIsVaccinated(request.getVaccinated());
        pet.setFid(request.getFatherBreedId());
        pet.setMid(request.getMotherBreedId());
        pet.setSellerId(sellerId);

        return petRepository.save(pet);
    }

    public Pet updatePet(Integer id, PetRequest request, Integer sellerId) {
        Pet pet = getPetById(id);
        
        // Check if the user is the seller
        if (!pet.getSellerId().equals(sellerId)) {
            throw new RuntimeException("Not authorized to update this pet");
        }

        pet.setPName(request.getName());
        pet.setPAge(request.getAgeMonths());
        pet.setGender(request.getGender().toString());
        pet.setPPhoto(request.getImgUrl());
        pet.setIsVaccinated(request.getVaccinated());
        pet.setFid(request.getFatherBreedId());
        pet.setMid(request.getMotherBreedId());

        return petRepository.save(pet);
    }

    public void deletePet(Integer id, Integer sellerId) {
        Pet pet = getPetById(id);
        
        // Check if the user is the seller
        if (!pet.getSellerId().equals(sellerId)) {
            throw new RuntimeException("Not authorized to delete this pet");
        }

        petRepository.delete(pet);
    }

    public List<Pet> searchPets(String searchTerm) {
        return petRepository.searchPetsByName(searchTerm);
    }

    public List<Pet> getPetsByBreed(Integer breedId) {
        return petRepository.findByFid(breedId);
    }

    public List<Pet> getPetsBySeller(Integer sellerId) {
        return petRepository.findBySellerId(sellerId);
    }
}
