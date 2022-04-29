package com.example.animal.clinic.back.service;

public interface EmailSenderService {
    void sendRegistrationEmail(String email, String username, String password);
    void sendPasswordRecoveryEmail(String email, String password);
    void sendPasswordResetEmail(String email, String password);
}
