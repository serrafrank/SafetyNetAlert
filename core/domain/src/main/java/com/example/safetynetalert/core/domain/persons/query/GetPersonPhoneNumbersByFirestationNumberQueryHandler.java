package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.query_pipeline.AbstractQueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonPhoneNumbersByFirestationNumberQueryHandler
    extends AbstractQueryHandler<
    GetPersonPhoneNumbersByFirestationNumberQuery, Set<String>> {

    private final PersonProjectionRepository personProjectionRepository;

    @Autowired
    public GetPersonPhoneNumbersByFirestationNumberQueryHandler(PersonProjectionRepository personProjectionRepository) {
        this.personProjectionRepository = personProjectionRepository;
    }

    @Override
    public Set<String> handler(GetPersonPhoneNumbersByFirestationNumberQuery query) {
        return personProjectionRepository.getPersonPhoneNumbersByFirestationNumber(query.firestationNumber());
    }
}
