package com.example.safetynetalert.core.domain.persons.command;

import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;

public interface PersonAggregatorRepository {

    void createPerson(PersonAggregate personAggregate);

}
