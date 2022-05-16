package com.example.animal.clinic.back.controller;

import com.example.animal.clinic.back.dto.MedicalServiceScheduleDto;
import com.example.animal.clinic.back.dto.MedicalServiceTypeDto;
import com.example.animal.clinic.back.dto.ServiceAppointmentDto;
import com.example.animal.clinic.back.service.MedicalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-services")
@RequiredArgsConstructor
public class MedicalServiceController {
    private final MedicalServiceService medicalServiceService;

    @GetMapping("/types")
    public List<MedicalServiceTypeDto> getMedicalServiceTypes() {
        return medicalServiceService.getMedicalServiceTypes();
    }

    @GetMapping("/type-names")
    public List<String> getMedicalServiceTypeNames() {
        return medicalServiceService.getMedicalServiceTypeNames();
    }

    @GetMapping("/schedules/{name}")
    public List<MedicalServiceScheduleDto> getMedicalServiceSchedules(@PathVariable String name) {
        return medicalServiceService.getMedicalServiceSchedulesByName(name);
    }

    @PostMapping("/make-appointment")
    public ServiceAppointmentDto makeMedicalServiceAppointment(
            @RequestBody ServiceAppointmentDto serviceAppointmentDto
    ) {
        return medicalServiceService.makeMedicalServiceAppointment(serviceAppointmentDto);
    }

    @PostMapping("/cancel-appointment")
    public ServiceAppointmentDto cancelMedicalServiceAppointment(
            @RequestBody ServiceAppointmentDto serviceAppointmentDto
    ) {
        return medicalServiceService.cancelMedicalServiceAppointment(serviceAppointmentDto);
    }
}
