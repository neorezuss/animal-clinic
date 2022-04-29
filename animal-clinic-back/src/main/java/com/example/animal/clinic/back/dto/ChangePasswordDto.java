package com.example.animal.clinic.back.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class ChangePasswordDto {
    @NotBlank(message = "Email may not be blank")
    @Email(message = "Incorrect email format")
    private String email;
    @NotBlank(message = "Password may not be blank")
    @Size(min = 8, max = 255, message = "Password length should be between 8 and 255 characters")
    private String oldPassword;
    @NotBlank(message = "Password may not be blank")
    @Size(min = 8, max = 255, message = "Password length should be between 8 and 255 characters")
    private String newPassword;
}
