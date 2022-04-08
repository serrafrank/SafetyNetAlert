package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.application.core.queryPipeline.AbstractQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetPersonByFirstnameAndLastnameQueryHandler extends AbstractQueryHandler<GetPersonByFirstnameAndLastnameQuery, Optional<PersonByFirstnameAndLastnameValueObject>> {

    @Autowired
    private PersonProjectionRepository personProjectionRepository;

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> handler(
            GetPersonByFirstnameAndLastnameQuery getPersonByFirstnameAndLastnameQuery) {

        return personProjectionRepository.GetPersonByFirstnameAndLastname(
                getPersonByFirstnameAndLastnameQuery.firstname(),
                getPersonByFirstnameAndLastnameQuery.lastname());
    }
}
