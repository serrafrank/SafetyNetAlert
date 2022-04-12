package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.AbstractQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GetPersonsWithMedicalRecordByFireStationNumbersQueryHandler
        extends
        AbstractQueryHandler<GetPersonsWithMedicalRecordByFireStationNumbersQuery, Set<PersonWithMedicalRecordsValueObject>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetPersonsWithMedicalRecordByFireStationNumbersQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<PersonWithMedicalRecordsValueObject> handler(
            GetPersonsWithMedicalRecordByFireStationNumbersQuery query) {
        return repository.getPersonsWithMedicalRecordByFireStationNumbersQuery(query
                .stations());
    }
}
