package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.domaine.persons.command.Medication;
import com.example.safteynetlert.domaine.persons.command.PersonAggregate;
import java.util.List;

public record PersonByFirstnameAndLastnameValueObject(
    String firstName,
    String lastName,
    String address,
    String city,
    String zip,
    String email,
    Integer age,
    List<Medication> medications,
    List<String> allergies) {

    public PersonByFirstnameAndLastnameValueObject(PersonAggregate p) {
        this(p.firstName(),
            p.lastName(),
            p.address(),
            p.city(),
            p.zip(),
            p.email(),
            p.age(),
            p.medications(),
            p.allergies());
    }
}
