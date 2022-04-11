package com.example.safetynetalert.commons.pipeline_builder.validators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GenericValidationResultTest {

    @Test
    void throwsValidateWhenInitWithOk() {
        var result = new GenericValidationResult(true);
        assertThat(result.isValid()).isTrue();
        assertThat(result.isNotValid()).isFalse();
    }

    @Test
    void throwsValidateWhenInitWithFail() {
        var result = new GenericValidationResult(false);
        assertThat(result.isValid()).isFalse();
        assertThat(result.isNotValid()).isTrue();
    }

    @Test
    void shouldThrowAnExceptionExceptionWhenValueIsFalse() {
        var e =
            assertThrows(
                Exception.class,
                () -> new GenericValidationResult(false)
                    .orThrow(() -> new Exception("Throw Exception")));

        assertThat(e)
            .hasMessage(
                "Throw Exception");
    }

    @Test
    void notShouldThrowAnExceptionWhenValueIsTrue() throws Exception {
        var e = new GenericValidationResult(true).orThrow(() -> new Exception("Throw Exception"));
        assertThat(e).isTrue();

    }
}
