package com.pets.controller;

import com.pets.dto.PetRequest;
import com.pets.model.Pet;
import com.pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Integer id) {
        Pet pet = petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody PetRequest petRequest, Authentication authentication) {
        // Extract user ID from authentication (this would be set by JWT filter)
        Integer sellerId = 1; // For now, hardcode - in real app would extract from JWT
        Pet pet = petService.createPet(petRequest, sellerId);
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Integer id, @RequestBody PetRequest petRequest, Authentication authentication) {
        Integer sellerId = 1; // For now, hardcode - in real app would extract from JWT
        Pet pet = petService.updatePet(id, petRequest, sellerId);
        return ResponseEntity.ok(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id, Authentication authentication) {
        Integer sellerId = 1; // For now, hardcode - in real app would extract from JWT
        petService.deletePet(id, sellerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Pet>> searchPets(@RequestParam String q) {
        List<Pet> pets = petService.searchPets(q);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/breed/{breedId}")
    public ResponseEntity<List<Pet>> getPetsByBreed(@PathVariable Integer breedId) {
        List<Pet> pets = petService.getPetsByBreed(breedId);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Pet>> getPetsBySeller(@PathVariable Integer sellerId) {
        List<Pet> pets = petService.getPetsBySeller(sellerId);
        return ResponseEntity.ok(pets);
    }
}
