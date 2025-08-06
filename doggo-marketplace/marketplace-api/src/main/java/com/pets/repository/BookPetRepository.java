package com.pets.repository;

import com.pets.model.BookPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookPetRepository extends JpaRepository<BookPet, Integer> {
    List<BookPet> findByBuyerId(Integer buyerId);
    List<BookPet> findByPId(Integer pId);
    Optional<BookPet> findByBkId(Integer bkId);
    
    @Query("SELECT bp FROM BookPet bp WHERE bp.date = :date")
    List<BookPet> findByDate(@Param("date") LocalDate date);
    
    @Query("SELECT bp FROM BookPet bp WHERE bp.date BETWEEN :startDate AND :endDate")
    List<BookPet> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
} 