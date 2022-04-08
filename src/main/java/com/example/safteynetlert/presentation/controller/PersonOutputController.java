package com.example.safteynetlert.presentation.controller;

import com.example.safteynetlert.application.PersonUseCase;
import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safteynetlert.presentation.exceptions.ResourceNotFoundException;
import com.example.safteynetlert.presentation.io.output.PersonInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonOutputController {
    private final PersonUseCase personUseCaser;

    public PersonOutputController(PersonUseCase personUseCaser) {
        this.personUseCaser = personUseCaser;
    }

    @GetMapping("personInfo")
    public PersonInfo getPersonInfo(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName) {

        PersonByFirstnameAndLastnameValueObject person = personUseCaser.getPersonByFirstnameAndLastname(
                        firstName,
                        lastName)
                .orElseThrow(ResourceNotFoundException::new);

        return new PersonInfo(person);

    }

}
