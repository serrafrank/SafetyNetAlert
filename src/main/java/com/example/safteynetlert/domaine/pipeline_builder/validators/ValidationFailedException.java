package com.example.safteynetlert.domaine.pipeline_builder.validators;

public class ValidationFailedException
        extends RuntimeException {

    public ValidationFailedException() {
        super("The pipeline validator failed");
    }
}
