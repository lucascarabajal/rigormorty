package com.disenio.rigormorty.exception;

public class EqualObjectException extends RuntimeException{

    public EqualObjectException(String errorMessage) {
        super(errorMessage);
    }

    public EqualObjectException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
