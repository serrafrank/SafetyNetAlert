package com.example.safetynetalert.core.domain.persons.aggregate;

import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;
import org.apache.commons.lang3.StringUtils;

public record Medication(
        String drug,
        String dose) {

    public Medication {
        PrettyValidation.test(drug).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
        PrettyValidation.test(dose).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
    }
}
