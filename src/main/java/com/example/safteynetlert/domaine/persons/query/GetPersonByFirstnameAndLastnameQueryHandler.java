package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.application.core.query_pipeline.AbstractQueryHandler;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonByFirstnameAndLastnameQueryHandler
    extends
    AbstractQueryHandler<GetPersonByFirstnameAndLastnameQuery, Optional<PersonByFirstnameAndLastnameValueObject>> {

    private final PersonProjectionRepository personProjectionRepository;

    @Autowired
    public GetPersonByFirstnameAndLastnameQueryHandler(PersonProjectionRepository personProjectionRepository) {
        this.personProjectionRepository = personProjectionRepository;
    }

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> handler(
        GetPersonByFirstnameAndLastnameQuery query) {

        return personProjectionRepository.getPersonByFirstnameAndLastname(
            query.firstname(),
            query.lastname());
    }
}
