package com.pets.repository;

import com.pets.model.Pet;
import com.pets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findBySoldOutFalse();
    List<Pet> findBySeller(User seller);
    List<Pet> findByBreedContainingIgnoreCase(String breed);
    
    @Query("SELECT p FROM Pet p WHERE p.soldOut = false AND (p.name LIKE %:searchTerm% OR p.breed LIKE %:searchTerm%)")
    List<Pet> searchPets(@Param("searchTerm") String searchTerm);
} 