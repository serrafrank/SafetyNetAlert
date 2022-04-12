package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.NullOrEmptyArgumentException;

import java.util.Set;

public record GetPersonsWithMedicalRecordByFireStationNumbersQuery(Set<Integer> stations)
        implements
        Query {
    public GetPersonsWithMedicalRecordByFireStationNumbersQuery {
        PrettyValidation.test(stations)
                .isNotNull()
                .orThrow(() -> new NullOrEmptyArgumentException("fireStationNumber"));
    }
}

