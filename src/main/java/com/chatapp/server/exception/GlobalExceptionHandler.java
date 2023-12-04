package com.chatapp.server.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleException(
            ResourceNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "the resource were not found" ,
                details);

        return ResponseEntityBuilder.build(err);
    }


    @ExceptionHandler({DuplicationResourceException.class})
    public ResponseEntity<Object> handleException(
            DuplicationResourceException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "the resource were duplicated" ,
                details);

        return ResponseEntityBuilder.build(err);
    }


    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleException(
            BadCredentialsException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "the resource weren't corrected" ,
                details);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleSecurityException(Exception ex, HttpServletRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ApiError err = new ApiError() ;
        err.setErrors(details);
        err.setOccurredAt(LocalDateTime.now());
        return ResponseEntityBuilder.build(err) ;
    }

}
