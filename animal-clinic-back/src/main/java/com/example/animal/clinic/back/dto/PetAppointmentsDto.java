package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Builder
@Getter
public class PetAppointmentsDto {
    private Long id;
    private String name;
    private String petType;
    private String breed;
    private Date birthDate;
    List<MedicalServiceAppointmentDto> appointments;
}
