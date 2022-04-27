package com.example.animal.clinic.back.dto;

import com.example.animal.clinic.back.entity.PetTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Builder
@Getter
@Setter
@ToString
public class PetDto {
    private Long id;
    private String name;
    private PetTypeEnum petType;
    private Date birthDate;
}
