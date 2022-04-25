package com.example.animal.clinic.back.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {
    private HttpStatus status;
    private String message;
    private long timeStamp;
}
