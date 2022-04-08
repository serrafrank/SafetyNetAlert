package com.example.safteynetlert.application;

import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;

import java.util.Optional;

public interface PersonUseCase {

    Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
            String firstname,
            String lastname);

}
