package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;
import lombok.Getter;

public final class GetChildrenByAddressWithFamilyMembersQuery
    extends Query {

    @Getter
    private final String address;

    public GetChildrenByAddressWithFamilyMembersQuery(String address) {
        PrettyValidation.test(address)
            .isNot(String::isBlank)
            .orThrow(() -> new BlankArgumentException("address"));
        this.address = address;
    }
}
