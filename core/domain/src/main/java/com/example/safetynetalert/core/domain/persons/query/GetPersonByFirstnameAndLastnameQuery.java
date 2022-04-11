package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;

public record GetPersonByFirstnameAndLastnameQuery(String firstname,
                                                   String lastname)
    implements Query {

}
