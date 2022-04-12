package com.example.safetynetalert.core.domain.exceptions;

public class NullOrEmptyArgumentException extends GenericBadRequestException {
    public NullOrEmptyArgumentException() {
        super("Argument cannot be null or empty");
    }

    public NullOrEmptyArgumentException(String argument) {
        super(argument + " cannot be null or empty");
    }
}
