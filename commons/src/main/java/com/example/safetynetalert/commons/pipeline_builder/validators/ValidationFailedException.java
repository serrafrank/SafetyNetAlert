package com.example.safetynetalert.commons.pipeline_builder.validators;

import com.example.safetynetalert.commons.exception.GenericInternalServerErrorException;

public class ValidationFailedException
        extends GenericInternalServerErrorException {

    public ValidationFailedException() {
        super("The pipeline validator failed");
    }
}
