package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Getter
@Builder
public class MedicalServiceScheduleDto {
    private Long id;
    private SpecialistDto specialist;
    private Set<String> availablePetTypes;
    private Date date;
    private Time time;
    private Integer officeNumber;
}
