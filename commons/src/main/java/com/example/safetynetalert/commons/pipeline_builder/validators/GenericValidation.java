package com.example.safetynetalert.commons.pipeline_builder.validators;

import java.util.function.Predicate;

public record GenericValidation<TParam>(TParam param) {

    public static <TParam> GenericValidation<TParam> from(TParam param) {
        return new GenericValidation<>(param);
    }

    public GenericValidationResult expected(Predicate<TParam> predicate) {
        return new  GenericValidationResult(predicate.test(param()));
    }
}
