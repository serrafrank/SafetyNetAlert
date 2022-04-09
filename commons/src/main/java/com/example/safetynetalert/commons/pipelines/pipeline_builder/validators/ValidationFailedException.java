package com.example.safetynetalert.commons.pipelines.pipeline_builder.validators;

public class ValidationFailedException
    extends RuntimeException {

    public ValidationFailedException() {
        super("The pipeline validator failed");
    }
}
