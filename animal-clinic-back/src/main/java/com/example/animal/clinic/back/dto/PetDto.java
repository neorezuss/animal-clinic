package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class PetDto {
    private Long id;
    private String name;
    private String petType;
    private String breed;
    private Date birthDate;
}
