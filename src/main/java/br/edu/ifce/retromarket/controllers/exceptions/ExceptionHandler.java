package br.edu.ifce.retromarket.controllers.exceptions;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandler {
    
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFound e, jakarta.servlet.http.HttpServletRequest req) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        CustomError err = new CustomError(
            Instant.now(),
            status.value(),
            e.getMessage(),
            req.getRequestURI()
        );
        
        return ResponseEntity
            .status(status)
            .body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException ex, HttpServletRequest req) {

        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.UNPROCESSABLE_CONTENT.value()); 
        error.setError("Dados Inválidos.");
        error.setPath(req.getRequestURI());


        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
}

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error);
}
}