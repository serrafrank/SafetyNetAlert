package com.example.safetynetalert.core.domain.exceptions;

import com.example.safetynetalert.commons.exception.GenericBadRequestException;

public class BlankArgumentException extends GenericBadRequestException {
    public BlankArgumentException() {
        super("Argument cannot be blank");
    }

    public BlankArgumentException(String argument) {
        super(argument + " cannot be blank");
    }
}
