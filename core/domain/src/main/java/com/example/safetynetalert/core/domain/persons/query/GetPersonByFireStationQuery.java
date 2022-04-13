package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.NullOrEmptyArgumentException;
import lombok.Getter;

public final class GetPersonByFireStationQuery
    extends Query {

    @Getter
    private final Integer fireStationNumber;

    public GetPersonByFireStationQuery(Integer fireStationNumber) {
        PrettyValidation.test(fireStationNumber)
            .isNotNull()
            .orThrow(() -> new NullOrEmptyArgumentException("fireStationNumber"));
        this.fireStationNumber = fireStationNumber;
    }
}
