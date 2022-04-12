package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;

public record GetEmailsByCityQuery(String city)
        implements Query {
    public GetEmailsByCityQuery {
        PrettyValidation.test(city)
                .isNot(String::isBlank)
                .orThrow(() -> new BlankArgumentException("city"));
    }
}