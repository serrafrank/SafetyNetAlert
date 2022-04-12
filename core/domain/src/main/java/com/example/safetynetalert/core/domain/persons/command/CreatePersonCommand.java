package com.example.safetynetalert.core.domain.persons.command;

import com.example.safetynetalert.commons.pipelines.command_pipeline.Command;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;
import org.apache.commons.lang3.StringUtils;

public record CreatePersonCommand(
        String firstName,
        String lastName,
        String address,
        String city,
        String zip,
        String phone,
        String email) implements Command {

    public CreatePersonCommand {
        PrettyValidation.test(firstName).isNot(StringUtils::isBlank).orThrow(() -> new BlankArgumentException("firstName"));
        PrettyValidation.test(lastName).isNot(StringUtils::isBlank).orThrow(() -> new BlankArgumentException("lastName"));
        PrettyValidation.test(address).isNot(StringUtils::isBlank).orThrow(() -> new BlankArgumentException("address"));
        PrettyValidation.test(city).isNot(StringUtils::isBlank).orThrow(() -> new BlankArgumentException("city"));
        PrettyValidation.test(zip).isNot(StringUtils::isBlank).orThrow(() -> new BlankArgumentException("zip"));
        PrettyValidation.test(phone).isNot(StringUtils::isBlank).orThrow(() -> new BlankArgumentException("phone"));
        PrettyValidation.test(email).isNot(StringUtils::isBlank).orThrow(() -> new BlankArgumentException("email"));
    }

}
