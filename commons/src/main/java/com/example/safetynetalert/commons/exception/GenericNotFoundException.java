package com.example.safetynetalert.commons.exception;

public class GenericNotFoundException extends
        RuntimeException {

    public GenericNotFoundException() {
        super("Resource cannot be found");
    }

    public GenericNotFoundException(String id) {
        super("Resource with id = " + id + " cannot be found.");
    }
}
