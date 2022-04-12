package com.example.safetynetalert.core.domain.persons.aggregate;

import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;
import org.apache.commons.lang3.StringUtils;

public record Id(String id) {

    public Id {
        PrettyValidation.test(id).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
    }

    public Id(String... elements) {
        this(String.join(":", elements));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Id testerId = (Id) o;

        return id.equals(testerId.id());
    }
}
