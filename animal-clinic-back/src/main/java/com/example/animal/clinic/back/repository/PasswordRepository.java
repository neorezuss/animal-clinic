package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    Password findByUserEmail(String email);
}
