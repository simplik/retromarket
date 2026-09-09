package br.edu.ifce.retromarket.controllers.exceptions;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}