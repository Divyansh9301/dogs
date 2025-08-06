package com.pets.repository;

import com.pets.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {
    Optional<Breed> findByBname(String bname);
    boolean existsByBname(String bname);
    Optional<Breed> findByBid(Integer bid);
} 