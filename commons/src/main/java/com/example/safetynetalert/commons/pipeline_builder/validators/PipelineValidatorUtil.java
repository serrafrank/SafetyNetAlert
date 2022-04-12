package com.example.safetynetalert.commons.pipeline_builder.validators;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class PipelineValidatorUtil {

    private PipelineValidatorUtil() {
    }
    public static <TObject> Predicate<List<TObject>> notEmpty() {
        return s -> Objects.nonNull(s) && ObjectUtils.isNotEmpty(s);
    }

    public static <TObject> Predicate<List<TObject>> onlyOne() {
        return s -> Objects.nonNull(s) && ObjectUtils.isNotEmpty(s) && s.size() == 1;
    }

    public static <TObject> Predicate<List<TObject>> greaterThanOne() {
        return s -> Objects.nonNull(s) && ObjectUtils.isNotEmpty(s) &&  s.size() > 1;
    }
}
