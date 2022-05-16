package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Builder
public class MedicalServiceAppointmentDto {
    private Long id;
    private String name;
    private SpecialistDto specialist;
    private Date date;
    private Time time;
    private Integer officeNumber;
    private String result;
}
