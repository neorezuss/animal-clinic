package com.example.animal.clinic.back.dto;

import com.example.animal.clinic.back.entity.PetType;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class PetDto {
    private Long id;
    private String name;
    private PetType petType;
    private Date birthDate;
}
