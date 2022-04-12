package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;

public record GetChildrenByAddressWithFamilyMembersQuery(String address)
        implements Query {
    public GetChildrenByAddressWithFamilyMembersQuery {
        PrettyValidation.test(address)
                .isNot(String::isBlank)
                .orThrow(() -> new BlankArgumentException("address"));
    }
}
