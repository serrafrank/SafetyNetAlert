package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.application.core.query_pipeline.AbstractQueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonByFirestationQueryHandler
    extends
    AbstractQueryHandler<GetPersonByFirestationQuery, Set<PersonByFirestationValueObject>> {

    private final PersonProjectionRepository personProjectionRepository;

    @Autowired
    public GetPersonByFirestationQueryHandler(PersonProjectionRepository personProjectionRepository) {
        this.personProjectionRepository = personProjectionRepository;
    }

    @Override
    public Set<PersonByFirestationValueObject> handler(GetPersonByFirestationQuery query) {
        return personProjectionRepository.getPersonByFirestation(query.firestationNumber());
    }
}
