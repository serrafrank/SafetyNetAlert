package com.example.safetynetalert.core.presentation.exceptions;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException
    extends AbstractResponseStatusException {

    public ResourceNotFoundException() {
        super("Resource cannot be found");
    }

    public ResourceNotFoundException(@NonNull String id) {
        super("Resource with id = " + id + " cannot be found.");
    }

}
