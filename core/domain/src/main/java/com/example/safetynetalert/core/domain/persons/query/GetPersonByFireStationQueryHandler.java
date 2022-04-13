package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonByFireStationQueryHandler
    implements QueryHandler<GetPersonByFireStationQuery, Set<PersonByFireStationValueObject>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetPersonByFireStationQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<PersonByFireStationValueObject> handler(GetPersonByFireStationQuery query) {
        return repository.getPersonByFireStation(query.getFireStationNumber());
    }
}
