package com.example.animal.clinic.back.exception;

public class MedicalServiceAppointmentIsAlreadyTakenException extends RuntimeException {
    public MedicalServiceAppointmentIsAlreadyTakenException() {
    }

    public MedicalServiceAppointmentIsAlreadyTakenException(String message) {
        super(message);
    }

    public MedicalServiceAppointmentIsAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicalServiceAppointmentIsAlreadyTakenException(Throwable cause) {
        super(cause);
    }
}
