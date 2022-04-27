package com.example.animal.clinic.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendRegistrationEmail(String email, String username, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Регистрация в системе Vet Clinic");
        message.setText(
                username + ", спасибо за регистрацию учетной записи в системе Vet Clinic.\n\n" +
                        "Можно использовать следующие данные для входа на сайт:\n\n" +
                        "Email: " + email + "\n" +
                        "Password: " + password + "\n\n" +
                        "Пожалуйста, запомните их и держите в надежном месте.\n\n" +
                        "С наилучшими пожеланиями,\n" +
                        "Команда Vet Clinic."
        );
        mailSender.send(message);
    }

    @Override
    public void sendPasswordRecoveryEmail(String email, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Восстановление пароля в системе Vet Clinic");
        message.setText(
                "Если вы не оставляли запрос на восстановление пароля, просто проигнорируйте это письмо!\n\n" +
                        "Используйте следующие данные для входа на сайт:\n\n" +
                        "Email: " + email + "\n" +
                        "Password: " + password + "\n\n" +
                        "Пожалуйста, запомните их и держите в надежном месте.\n\n" +
                        "С наилучшими пожеланиями,\n" +
                        "Команда Vet Clinic."
        );
        mailSender.send(message);
    }
}
