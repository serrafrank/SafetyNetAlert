package com.example.safteynetlert.application;

import com.example.safteynetlert.domaine.persons.command.PersonAggregate;

public interface PersonUseCase {

    PersonAggregate getPersonByFirstnameAndLastname(String firstname, String lastname);

}
