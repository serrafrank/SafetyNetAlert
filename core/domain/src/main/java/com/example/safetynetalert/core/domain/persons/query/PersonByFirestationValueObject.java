package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;

public record PersonByFirestationValueObject(
    String firstName,
    String lastName,
    String address,
    String city,
    String zip,
    String phone,
    Boolean isMinor) {

    public PersonByFirestationValueObject(PersonAggregate p) {
        this(p.firstName(),
            p.lastName(),
            p.address(),
            p.city(),
            p.zip(),
            p.phone(),
            p.isMinor());
    }
}
