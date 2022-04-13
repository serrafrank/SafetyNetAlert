package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPersonPhoneNumbersByFireStationNumberQueryHandler
    implements QueryHandler<
    GetPersonPhoneNumbersByFireStationNumberQuery, Set<String>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetPersonPhoneNumbersByFireStationNumberQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<String> handler(GetPersonPhoneNumbersByFireStationNumberQuery query) {
        return repository.getPersonPhoneNumbersByFireStationNumber(query.getFireStationNumber());
    }
}
