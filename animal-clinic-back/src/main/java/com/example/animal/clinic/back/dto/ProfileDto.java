package com.example.animal.clinic.back.dto;

import com.example.animal.clinic.back.entity.GenderEnum;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.util.Set;

@Getter
@Builder
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private GenderEnum gender;
    private Date birthDate;
    private String phoneNumber;
    private String email;
    private Set<PetDto> pets;
}
