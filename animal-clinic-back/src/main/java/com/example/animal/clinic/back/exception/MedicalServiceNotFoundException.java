package com.example.animal.clinic.back.exception;

public class MedicalServiceNotFoundException extends RuntimeException {
    public MedicalServiceNotFoundException() {
    }

    public MedicalServiceNotFoundException(String message) {
        super(message);
    }

    public MedicalServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicalServiceNotFoundException(Throwable cause) {
        super(cause);
    }
}
