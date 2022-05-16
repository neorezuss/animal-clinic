package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.MedicalServiceScheduleDto;
import com.example.animal.clinic.back.dto.MedicalServiceTypeDto;
import com.example.animal.clinic.back.dto.ServiceAppointmentDto;

import java.util.List;

public interface MedicalServiceService {
    List<MedicalServiceTypeDto> getMedicalServiceTypes();

    List<MedicalServiceScheduleDto> getMedicalServiceSchedulesByName(String name);

    List<String> getMedicalServiceTypeNames();

    ServiceAppointmentDto makeMedicalServiceAppointment(ServiceAppointmentDto serviceAppointmentDto);

    ServiceAppointmentDto cancelMedicalServiceAppointment(ServiceAppointmentDto serviceAppointmentDto);
}
