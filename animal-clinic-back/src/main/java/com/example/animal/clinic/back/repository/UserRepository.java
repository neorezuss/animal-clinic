package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.Pet;
import com.example.animal.clinic.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndPetsContains(String email, Pet pet);
}
