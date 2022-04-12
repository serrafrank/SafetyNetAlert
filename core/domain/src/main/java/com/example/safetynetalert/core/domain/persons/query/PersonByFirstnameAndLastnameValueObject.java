package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.core.domain.persons.aggregate.Medication;
import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;

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
                p.medicalRecord().medications(),
                p.medicalRecord().allergies());
    }
}
