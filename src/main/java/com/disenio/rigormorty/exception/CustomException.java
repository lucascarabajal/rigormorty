package com.disenio.rigormorty.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus
public class CustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
