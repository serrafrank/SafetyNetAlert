package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.AbstractQueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonsWithMedicalRecordByFirestationNumbersQueryHandler
    extends
    AbstractQueryHandler<GetPersonsWithMedicalRecordByFirestationNumbersQuery, Set<PersonWithMedicalRecordsValueObject>> {

    private final PersonProjectionRepository personProjectionRepository;

    @Autowired
    public GetPersonsWithMedicalRecordByFirestationNumbersQueryHandler(PersonProjectionRepository personProjectionRepository) {
        this.personProjectionRepository = personProjectionRepository;
    }

    @Override
    public Set<PersonWithMedicalRecordsValueObject> handler(
        GetPersonsWithMedicalRecordByFirestationNumbersQuery query) {
        return personProjectionRepository.getPersonsWithMedicalRecordByFirestationNumbersQuery(query
            .stations());
    }
}
