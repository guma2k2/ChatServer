package com.chatapp.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicationResourceException extends RuntimeException{
    public DuplicationResourceException(String message) {
        super(message);
    }
}
