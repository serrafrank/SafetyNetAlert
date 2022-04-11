package com.example.safetynetalert.commons.pipeline_builder.validators;

public class ValidationFailedException
    extends RuntimeException {

    public ValidationFailedException() {
        super("The pipeline validator failed");
    }
}
