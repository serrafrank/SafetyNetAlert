package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.AbstractQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GetPersonByFireStationQueryHandler
        extends
        AbstractQueryHandler<GetPersonByFireStationQuery, Set<PersonByFireStationValueObject>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetPersonByFireStationQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<PersonByFireStationValueObject> handler(GetPersonByFireStationQuery query) {
        return repository.getPersonByFireStation(query.fireStationNumber());
    }
}
