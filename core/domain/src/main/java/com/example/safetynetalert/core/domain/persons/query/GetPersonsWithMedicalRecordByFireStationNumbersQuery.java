package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.NullOrEmptyArgumentException;
import java.util.Set;
import lombok.Getter;

public final class GetPersonsWithMedicalRecordByFireStationNumbersQuery
    extends Query {

    @Getter
    private final Set<Integer> stations;

    public GetPersonsWithMedicalRecordByFireStationNumbersQuery(Set<Integer> stations) {
        PrettyValidation.test(stations)
            .isNotNull()
            .orThrow(() -> new NullOrEmptyArgumentException("fireStationNumber"));
        this.stations = stations;
    }
}

