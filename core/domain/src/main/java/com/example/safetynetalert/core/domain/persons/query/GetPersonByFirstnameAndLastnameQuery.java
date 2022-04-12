package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;

public record GetPersonByFirstnameAndLastnameQuery(String firstname,
                                                   String lastname)
        implements Query {

    public GetPersonByFirstnameAndLastnameQuery {
        PrettyValidation.test(firstname)
                .isNot(String::isBlank)
                .orThrow(() -> new BlankArgumentException("firstname"));

        PrettyValidation.test(lastname)
                .isNot(String::isBlank)
                .orThrow(() -> new BlankArgumentException("lastname"));
    }
}
