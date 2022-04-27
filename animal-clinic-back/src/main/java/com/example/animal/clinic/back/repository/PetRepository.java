package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
