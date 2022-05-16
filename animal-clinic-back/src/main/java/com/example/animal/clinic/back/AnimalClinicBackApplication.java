package com.example.animal.clinic.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AnimalClinicBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalClinicBackApplication.class, args);
    }
}
