package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
    List<MedicalService> findAllByMedicalServiceTypeNameAndPetIsNullAndDateAfterOrderByDateAscTimeAsc(String name, Date date);
}
