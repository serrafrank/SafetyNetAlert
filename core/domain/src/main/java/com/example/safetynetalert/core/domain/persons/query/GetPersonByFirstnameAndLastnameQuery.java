package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;
import lombok.Getter;

public final class GetPersonByFirstnameAndLastnameQuery
    extends Query {

    @Getter
    private final String firstname;

    @Getter
    private final String lastname;

    public GetPersonByFirstnameAndLastnameQuery(String firstname, String lastname) {
        PrettyValidation.test(firstname)
            .isNot(String::isBlank)
            .orThrow(() -> new BlankArgumentException("firstname"));

        PrettyValidation.test(lastname)
            .isNot(String::isBlank)
            .orThrow(() -> new BlankArgumentException("lastname"));
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
