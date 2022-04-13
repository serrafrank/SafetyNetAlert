package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetEmailsByCityQueryHandler
    implements QueryHandler<GetEmailsByCityQuery, Set<String>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetEmailsByCityQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<String> handler(GetEmailsByCityQuery query) {
        return repository.getEmailsByCity(query.getCity());
    }

}
