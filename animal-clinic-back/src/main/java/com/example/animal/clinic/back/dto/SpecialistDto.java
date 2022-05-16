package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpecialistDto {
    private String firstName;
    private String lastName;
    private String patronymic;
}
