package com.pets.controller;

import com.pets.dto.PetRequest;
import com.pets.model.Pet;
import com.pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:5173")
public class PetController {
    
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        try {
            Pet pet = petService.getPetById(id);
            return ResponseEntity.ok(pet);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody PetRequest request, @RequestParam Long sellerId) {
        try {
            Pet pet = petService.createPet(request, sellerId);
            return ResponseEntity.ok(pet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @RequestBody PetRequest request, @RequestParam Long sellerId) {
        try {
            Pet pet = petService.updatePet(id, request, sellerId);
            return ResponseEntity.ok(pet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id, @RequestParam Long sellerId) {
        try {
            petService.deletePet(id, sellerId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Pet>> searchPets(@RequestParam String q) {
        List<Pet> pets = petService.searchPets(q);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<Pet>> getPetsByBreed(@PathVariable String breed) {
        List<Pet> pets = petService.getPetsByBreed(breed);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Pet>> getPetsBySeller(@PathVariable Long sellerId) {
        List<Pet> pets = petService.getPetsBySeller(sellerId);
        return ResponseEntity.ok(pets);
    }
} 