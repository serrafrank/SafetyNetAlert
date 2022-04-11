package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import java.util.Set;

public record GetPersonsWithMedicalRecordByFirestationNumbersQuery(Set<Integer> stations)
    implements
    Query {

}
