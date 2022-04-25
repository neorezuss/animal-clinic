package com.example.animal.clinic.back.dto;

import com.example.animal.clinic.back.entity.Gender;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Gender gender;
    private Date birthDate;
    private String phoneNumber;
    private String email;
}
