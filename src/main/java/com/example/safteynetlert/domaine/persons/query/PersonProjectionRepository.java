package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.infrastructure.models.Person;

import java.util.Optional;

public interface PersonProjectionRepository {
    Optional<PersonByFirstnameAndLastnameValueObject> GetPersonByFirstnameAndLastname(String firstname,
                                                     String lastname);
}
