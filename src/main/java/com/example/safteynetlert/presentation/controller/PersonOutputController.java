package com.example.safteynetlert.presentation.controller;

import com.example.safteynetlert.application.PersonUseCase;
import com.example.safteynetlert.domaine.persons.command.PersonAggregate;
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

    @GetMapping("personInfo?firstName=<firstName>&lastName=<lastName")
    public PersonInfo getPersonInfo(@RequestParam String firstName,
                                    @RequestParam String lastName) {

        PersonAggregate person = personUseCaser.getPersonByFirstnameAndLastname(
                firstName,
                lastName);

        return new PersonInfo(person);

    }

}
