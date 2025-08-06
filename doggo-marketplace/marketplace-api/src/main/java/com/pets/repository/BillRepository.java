package com.pets.repository;

import com.pets.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByUseracc(Long useracc);
    Optional<Bill> findByBkId(Integer bkId);
    Optional<Bill> findByBlid(Integer blid);
    
    @Query("SELECT b FROM Bill b WHERE b.date = :date")
    List<Bill> findByDate(@Param("date") LocalDate date);
    
    @Query("SELECT b FROM Bill b WHERE b.date BETWEEN :startDate AND :endDate")
    List<Bill> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    @Query("SELECT SUM(b.amt) FROM Bill b WHERE b.useracc = :useracc")
    Float getTotalAmountByUser(@Param("useracc") Long useracc);
} 