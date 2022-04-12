package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;

public record PersonByFireStationValueObject(
        String firstName,
        String lastName,
        String address,
        String city,
        String zip,
        String phone,
        Boolean isMinor) {

    public PersonByFireStationValueObject(PersonAggregate p) {
        this(p.firstName(),
                p.lastName(),
                p.address(),
                p.city(),
                p.zip(),
                p.phone(),
                p.isMinor());
    }
}
