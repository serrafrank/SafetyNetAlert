package com.example.safteynetlert.application;

import com.example.safteynetlert.core.queryPipeline.QueryBus;
import com.example.safteynetlert.domaine.persons.command.PersonAggregate;
import com.example.safteynetlert.domaine.persons.query.GetPersonByFirstnameAndLastnameQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonUseCaseImpl implements PersonUseCase {

    @Autowired
    private QueryBus queryBus;


    @Override
    public PersonAggregate getPersonByFirstnameAndLastname(String firstname,
                                                           String lastname) {
        Optional<PersonAggregate> person =
                queryBus.dispatch(new GetPersonByFirstnameAndLastnameQuery(firstname, lastname));

        return person.orElseThrow();

    }
}
