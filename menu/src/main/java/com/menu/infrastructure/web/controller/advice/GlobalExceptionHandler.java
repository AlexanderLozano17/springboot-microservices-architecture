package com.menu.infrastructure.web.controller.advice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.menu.infrastructure.web.model.response.ErrorResponse;
import com.menu.infrastructure.web.model.response.ValidationError;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        
    	List<ValidationError> errors = ex.getBindingResult()
    		    .getFieldErrors()
    		    .stream()
    		    .map(err -> ValidationError.builder()
    		        .field(err.getField())
    		        .message(err.getDefaultMessage())
    		        .build())
    		    .collect(Collectors.toList());

    	ErrorResponse response = ErrorResponse.builder()
    			.timestamp(LocalDateTime.now())
    			.status(HttpStatus.BAD_REQUEST.value())
    			.error("Error de validación")
    			.message("Uno o más campos tienen errores. Corrígelos e inténtalo de nuevo.")
    			.errors(errors)
    			.build();

        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundObjectExceptionHandler(Exception ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .reduce((first, second) -> first + ", " + second)
                .orElse("Validation errors");
        
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingParams(MissingServletRequestParameterException ex) {
        String errorMessage = "Missing parameter: " + ex.getParameterName();
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("Parameter '%s' should be of type %s. Provided value: %s",
                ex.getName(),
                Optional.ofNullable(ex.getRequiredType()).map(Class::getSimpleName).orElse("unknown"),
                ex.getValue()
        );

        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
