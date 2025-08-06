package com.pets.repository;

import com.pets.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findBySellerId(Integer sellerId);
    List<Pet> findByFid(Integer fid);
    List<Pet> findByMid(Integer mid);
    Optional<Pet> findByPId(Integer pId);
    
    @Query("SELECT p FROM Pet p WHERE p.pName LIKE %:searchTerm%")
    List<Pet> searchPetsByName(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT p FROM Pet p WHERE p.gender = :gender")
    List<Pet> findByGender(@Param("gender") String gender);
    
    @Query("SELECT p FROM Pet p WHERE p.isVaccinated = :vaccinated")
    List<Pet> findByVaccinationStatus(@Param("vaccinated") Boolean vaccinated);
} 