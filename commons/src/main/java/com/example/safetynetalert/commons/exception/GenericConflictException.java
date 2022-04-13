package com.example.safetynetalert.commons.exception;

public class GenericConflictException extends RuntimeException {

    public GenericConflictException() {
    }

    public GenericConflictException(String message) {
        super(message);
    }

    public GenericConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericConflictException(Throwable cause) {
        super(cause);
    }

    public GenericConflictException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
