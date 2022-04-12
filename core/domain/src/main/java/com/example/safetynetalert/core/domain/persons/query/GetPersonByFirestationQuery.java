package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.NullOrEmptyArgumentException;

public record GetPersonByFirestationQuery(Integer firestationNumber)
        implements Query {

    public GetPersonByFirestationQuery {
        PrettyValidation.test(firestationNumber)
                .isNotNull()
                .orThrow(() -> new NullOrEmptyArgumentException("firestationNumber"));
    }
}
