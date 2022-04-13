package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryHandler;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonByFirstnameAndLastnameQueryHandler
    implements
    QueryHandler<GetPersonByFirstnameAndLastnameQuery, Optional<PersonByFirstnameAndLastnameValueObject>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetPersonByFirstnameAndLastnameQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> handler(
            GetPersonByFirstnameAndLastnameQuery query) {

        return repository.getPersonByFirstnameAndLastname(
                query.getFirstname(),
                query.getLastname());
    }
}
