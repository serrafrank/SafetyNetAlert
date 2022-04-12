package com.example.safetynetalert.core.presentation.controllers;

import com.example.safetynetalert.core.presentation.io.input.CreatePersonResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("person")
public class PersonInputController {

    @PostMapping
    public ResponseEntity postPerson(@RequestBody CreatePersonResource createPersonResource){


        return new ResponseEntity(HttpStatus.CREATED);
    }
}
