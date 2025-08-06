package com.pets.repository;

import com.pets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailId(String emailId);
    boolean existsByEmailId(String emailId);
    Optional<User> findByUserid(Long userid);
    Optional<User> findByContactNo(String contactNo);
    boolean existsByContactNo(String contactNo);
    boolean existsByAadharNo(Long aadharNo);
} 