package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
    List<MedicalService> findAllByMedicalServiceTypeNameAndPetIsNullAndDateAfterOrderByDateAscTimeAsc(String name, Date date);
}
