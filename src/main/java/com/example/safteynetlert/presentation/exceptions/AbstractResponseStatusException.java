package com.example.safteynetlert.presentation.exceptions;

public abstract class AbstractResponseStatusException extends RuntimeException {
    public AbstractResponseStatusException() {
        super();
    }

    public AbstractResponseStatusException(final String message,
                                           final Throwable cause) {
        super(message, cause);
    }

    public AbstractResponseStatusException(final String message) {
        super(message);
    }

    public AbstractResponseStatusException(final Throwable cause) {
        super(cause);
    }
}
