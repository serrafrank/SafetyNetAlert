package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.AbstractQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetPersonByFirstnameAndLastnameQueryHandler
        extends
        AbstractQueryHandler<GetPersonByFirstnameAndLastnameQuery, Optional<PersonByFirstnameAndLastnameValueObject>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetPersonByFirstnameAndLastnameQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> handler(
            GetPersonByFirstnameAndLastnameQuery query) {

        return repository.getPersonByFirstnameAndLastname(
                query.firstname(),
                query.lastname());
    }
}
