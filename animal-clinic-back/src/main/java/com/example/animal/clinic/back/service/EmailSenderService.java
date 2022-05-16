package com.example.animal.clinic.back.service;

public interface EmailSenderService {
    void sendRegistrationEmail(String email, String username, String password);

    void sendPasswordResetEmail(String email, String password);

    void sendPasswordChangeEmail(String email, String password);
}
