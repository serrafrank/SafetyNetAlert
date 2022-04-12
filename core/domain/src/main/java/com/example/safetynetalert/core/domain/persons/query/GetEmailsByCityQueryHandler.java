package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.AbstractQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GetEmailsByCityQueryHandler
        extends
        AbstractQueryHandler<GetEmailsByCityQuery, Set<String>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetEmailsByCityQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<String> handler(GetEmailsByCityQuery query) {
        return repository.getEmailsByCity(query.city());
    }

}
