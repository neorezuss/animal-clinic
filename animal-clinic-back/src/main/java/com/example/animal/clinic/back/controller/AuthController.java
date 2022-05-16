package com.example.animal.clinic.back.controller;

import com.example.animal.clinic.back.dto.ChangePasswordDto;
import com.example.animal.clinic.back.dto.EmailDto;
import com.example.animal.clinic.back.dto.LoginDto;
import com.example.animal.clinic.back.dto.RefreshTokenDto;
import com.example.animal.clinic.back.dto.RegistrationDto;
import com.example.animal.clinic.back.security.AuthResponse;
import com.example.animal.clinic.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse authenticateUser(
            @Valid @RequestBody LoginDto loginDto
    ) {
        return authService.authenticateUser(loginDto);
    }

    @PostMapping("/register")
    public RegistrationDto registerUser(
            @Valid @RequestBody RegistrationDto registrationDto
    ) {
        return authService.registerUser(registrationDto);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(
            @Valid @RequestBody RefreshTokenDto refreshTokenDto
    ) {
        return authService.refreshTokens(refreshTokenDto);
    }

    @PostMapping("/reset-password")
    public boolean resetPassword(
            @RequestBody EmailDto emailDto
    ) {
        return authService.resetPassword(emailDto);
    }

    @PostMapping("/change-password")
    public boolean changePassword(
            @RequestBody ChangePasswordDto changePasswordDto
    ) {
        return authService.changePassword(changePasswordDto);
    }
}
