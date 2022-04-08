package com.example.safteynetlert.application;

import com.example.safteynetlert.application.core.queryPipeline.QueryBus;
import com.example.safteynetlert.domaine.persons.query.GetPersonByFirstnameAndLastnameQuery;
import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonUseCaseImpl implements PersonUseCase {

    @Autowired
    private QueryBus queryBus;

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
            String firstname,
            String lastname) {
        return queryBus.dispatch(new GetPersonByFirstnameAndLastnameQuery(
                firstname,
                lastname));

    }
}
