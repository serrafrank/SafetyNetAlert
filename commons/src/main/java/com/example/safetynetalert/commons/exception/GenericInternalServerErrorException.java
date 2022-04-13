package com.example.safetynetalert.commons.exception;

public class GenericInternalServerErrorException extends RuntimeException {

    public GenericInternalServerErrorException() {
    }

    public GenericInternalServerErrorException(String message) {
        super(message);
    }

    public GenericInternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericInternalServerErrorException(Throwable cause) {
        super(cause);
    }

    public GenericInternalServerErrorException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
