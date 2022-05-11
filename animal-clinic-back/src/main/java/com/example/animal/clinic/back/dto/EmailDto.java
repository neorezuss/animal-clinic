package com.example.animal.clinic.back.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class EmailDto {
    @NotBlank(message = "Email may not be blank")
    @Email(message = "Incorrect email format")
    private String email;
}
