package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.PetType;
import com.example.animal.clinic.back.entity.PetTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {
    PetType findByName(PetTypeEnum name);
}
