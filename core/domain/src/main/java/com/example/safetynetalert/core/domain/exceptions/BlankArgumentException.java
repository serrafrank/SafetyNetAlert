package com.example.safetynetalert.core.domain.exceptions;

public class BlankArgumentException extends GenericBadRequestException {
    public BlankArgumentException() {
        super("Argument cannot be blank");
    }

    public BlankArgumentException(String argument) {
        super(argument + " cannot be blank");
    }
}
