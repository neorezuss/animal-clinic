package com.example.animal.clinic.back.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RefreshTokenDto {
    @NotBlank(message = "Token may not be blank")
    private String refreshToken;
}
