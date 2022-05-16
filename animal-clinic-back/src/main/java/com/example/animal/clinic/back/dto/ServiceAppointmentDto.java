package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ServiceAppointmentDto {
    private Long medicalServiceId;
    private Long petId;
}
