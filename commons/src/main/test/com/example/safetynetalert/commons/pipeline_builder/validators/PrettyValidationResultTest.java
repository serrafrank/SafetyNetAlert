package com.example.safetynetalert.commons.pipeline_builder.validators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class PrettyValidationResultTest {

    @Test
    void throwsValidateWhenConditionIsValide() {
        var blankArgument = "   ";
        var result = PrettyValidation.test(blankArgument).is(StringUtils::isBlank);
        assertThat(result.isValid()).isTrue();
        assertThat(result.isNotValid()).isFalse();
    }

    @Test
    void throwsFailsWhenConditionIsValide() {
        var blankArgument = "   ";
        var result = PrettyValidation.test(blankArgument).isNot(StringUtils::isBlank);
        assertThat(result.isValid()).isFalse();
        assertThat(result.isNotValid()).isTrue();
    }

    @Test
    void throwsValidateWhenIsNonNullValue() {
        var result = PrettyValidation.test(true).isNotNull();
        assertThat(result.isValid()).isTrue();
        assertThat(result.isNotValid()).isFalse();
    }

    @Test
    void throwsValidateWhenIsNullValue() {
        var result = PrettyValidation.test(null).isNull();
        assertThat(result.isValid()).isTrue();
        assertThat(result.isNotValid()).isFalse();
    }

    @Test
    void throwsValidateWhenIsNonEmptyValue() {
        var result = PrettyValidation.test("not empty").isNotEmpty();
        assertThat(result.isValid()).isTrue();
        assertThat(result.isNotValid()).isFalse();
    }

    @Test
    void throwsValidateWhenIsEmptyValue() {
        var result = PrettyValidation.test("").isEmpty();
        assertThat(result.isValid()).isTrue();
        assertThat(result.isNotValid()).isFalse();
    }

    @Test
    void shouldThrowAnExceptionExceptionWhenValueIsNull() {
        var e = assertThrows(
                Exception.class,
                () -> PrettyValidation.test(null).isNotNull().orThrow(() -> new Exception("Throw Exception")));

        assertThat(e).hasMessage("Throw Exception");
    }

    @Test
    void notShouldThrowAnExceptionWhenValueIsNonNullValue() throws Exception {
        var e = PrettyValidation.test(true).isNotNull().orThrow(() -> new Exception("Throw Exception"));
        assertThat(e).isTrue();
    }
}
