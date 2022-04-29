package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.LoginDto;
import com.example.animal.clinic.back.dto.RefreshTokenDto;
import com.example.animal.clinic.back.dto.RegistrationDto;
import com.example.animal.clinic.back.dto.ChangePasswordDto;
import com.example.animal.clinic.back.security.AuthResponse;

public interface AuthService {
    AuthResponse authenticateUser(LoginDto loginDto);

    RegistrationDto registerUser(RegistrationDto registrationDto);

    AuthResponse refreshTokens(RefreshTokenDto refreshTokenDto);

    boolean resetPassword(String email);
    boolean changePassword(ChangePasswordDto changePasswordDto);
}
