package com.Project2.Employee.Management.Exception;

import com.Project2.Employee.Management.response.ApiResponse;
import jakarta.persistence.ElementCollection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateEmail(
            DuplicateEmailException exception) {
        ApiResponse<Void> response =
                new ApiResponse<>(
                        false,
                        exception.getMessage(),
                        null
                );
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFound(
            EmployeeNotFoundException exception)
    {
        Map<String, String> response = new HashMap<>();

        response.put("error", exception.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException exception)
    {
        Map<String, String> errors = new HashMap<>();


        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
        );
        ApiResponse<Map<String, String>> response =
                new ApiResponse<>(
                        false,
                        "Validation failed",
                        errors
                );

        return new ResponseEntity<>(
                response, HttpStatus.BAD_REQUEST
        );
    }
}
