package com.example.animal.clinic.back.dto;

import com.example.animal.clinic.back.entity.Gender;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
public class RegistrationDto {
    @NotBlank(message = "First name may not be blank")
    @Size(min = 2, max = 255, message = "First name length should be between 2 and 255 characters")
    private String firstName;
    @NotBlank(message = "Last name may not be blank")
    @Size(min = 2, max = 255, message = "First name length should be between 2 and 255 characters")
    private String lastName;
    @NotBlank(message = "Patronymic may not be blank")
    @Size(min = 2, max = 255, message = "Patronymic length should be between 2 and 255 characters")
    private String patronymic;
    private Gender gender;
    private Date birthDate;
    @NotBlank(message = "Phone number may not be blank")
    @Pattern(regexp = "(\\+375)(25|29|44)[0-9]{7}", message = "Phone number must be in +375(25|29|44)XXXXXXX format")
    private String phoneNumber;
    @NotBlank(message = "Email may not be blank")
    @Email(message = "Incorrect email format")
    private String email;
    @NotBlank(message = "Password may not be blank")
    @Size(min = 8, max = 255, message = "Password length should be between 8 and 255 characters")
    private String password;
}
