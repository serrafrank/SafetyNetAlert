package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.NullOrEmptyArgumentException;

public record GetPersonByFireStationQuery(Integer fireStationNumber)
        implements Query {

    public GetPersonByFireStationQuery {
        PrettyValidation.test(fireStationNumber)
                .isNotNull()
                .orThrow(() -> new NullOrEmptyArgumentException("fireStationNumber"));
    }
}
