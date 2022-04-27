package com.example.animal.clinic.back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), System.currentTimeMillis());
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handlePetNotFoundException(PetNotFoundException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), System.currentTimeMillis());
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.CONFLICT, exception.getLocalizedMessage(), System.currentTimeMillis());
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalStateException exception) {
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.CONFLICT, exception.getLocalizedMessage(), System.currentTimeMillis());
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder errors = new StringBuilder();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(fieldName).append(": ").append(errorMessage).append("; ");
        });
        ApiErrorResponse apiErrorResponse =
                new ApiErrorResponse(HttpStatus.BAD_REQUEST, errors.toString(), System.currentTimeMillis());
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }
}
