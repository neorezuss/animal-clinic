package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.MedicalServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalServiceTypeRepository extends JpaRepository<MedicalServiceType, Long> {
}
