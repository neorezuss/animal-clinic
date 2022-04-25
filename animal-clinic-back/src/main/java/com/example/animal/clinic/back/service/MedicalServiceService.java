package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.MedicalServiceTypeDto;

import java.util.List;

public interface MedicalServiceService {
    List<MedicalServiceTypeDto> getMedicalServiceTypes();
}
