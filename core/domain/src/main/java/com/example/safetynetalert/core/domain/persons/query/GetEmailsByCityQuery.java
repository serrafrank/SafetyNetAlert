package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;
import lombok.Getter;

public final class GetEmailsByCityQuery
    extends Query {

    @Getter
    private final String city;

    public GetEmailsByCityQuery(String city) {
        PrettyValidation.test(city)
            .isNot(String::isBlank)
            .orThrow(() -> new BlankArgumentException("city"));
        this.city = city;
    }
}
