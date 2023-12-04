package com.chatapp.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private LocalDateTime occurredAt;
    private HttpStatus status;
    private String message;
    private List errors;
}
