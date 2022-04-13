package com.example.safetynetalert.core.presentation.controllers;

import com.example.safetynetalert.core.application.CommandUseCase;
import com.example.safetynetalert.core.presentation.io.input.CreatePersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInputController {

    private final CommandUseCase commandUseCase;

    @Autowired
    public PersonInputController(CommandUseCase commandUseCase) {
        this.commandUseCase = commandUseCase;
    }

    @PostMapping("person")
    public ResponseEntity<Void> postPerson(@RequestBody CreatePersonResource createPersonResource) {
        commandUseCase.createPerson(
            createPersonResource.getFirstName(),
            createPersonResource.getLastName(),
            createPersonResource.getAddress(),
            createPersonResource.getCity(),
            createPersonResource.getZip(),
            createPersonResource.getPhone(),
            createPersonResource.getEmail());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
