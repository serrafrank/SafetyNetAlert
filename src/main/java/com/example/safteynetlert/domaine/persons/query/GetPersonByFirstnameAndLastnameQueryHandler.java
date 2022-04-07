package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.core.queryPipeline.AbstractQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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
