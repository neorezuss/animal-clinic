package com.example.animal.clinic.back.security;

import lombok.Value;

@Value
public class AuthResponse {
    String accessToken;
    String refreshToken;
}
