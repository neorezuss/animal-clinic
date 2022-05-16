package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByUserEmailOrderByName(String email);
}
