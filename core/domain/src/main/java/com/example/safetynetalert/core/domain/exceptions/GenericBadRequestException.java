package com.example.safetynetalert.core.domain.exceptions;

public class GenericBadRequestException extends RuntimeException {
    public GenericBadRequestException() {
    }

    public GenericBadRequestException(String message) {
        super(message);
    }

    public GenericBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericBadRequestException(Throwable cause) {
        super(cause);
    }

    public GenericBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
