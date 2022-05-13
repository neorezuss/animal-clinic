package com.example.animal.clinic.back.controller;

import com.example.animal.clinic.back.dto.MedicalServiceTypeDto;
import com.example.animal.clinic.back.entity.MedicalService;
import com.example.animal.clinic.back.service.MedicalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-services")
@RequiredArgsConstructor
public class MedicalServiceController {
    private final MedicalServiceService medicalServiceService;

    @GetMapping
    public List<MedicalServiceTypeDto> getMedicalServiceTypes() {
        return medicalServiceService.getMedicalServiceTypes();
    }

    @GetMapping
    public List<MedicalService> getMedicalServiceAppointmentsByType() {
        return medicalServiceService.getMedicalServiceTypes();
    }
}
