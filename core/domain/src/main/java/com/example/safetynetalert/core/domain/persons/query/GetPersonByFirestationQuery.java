package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.query_pipeline.Query;

public record GetPersonByFirestationQuery(Integer firestationNumber)
    implements Query {

}
