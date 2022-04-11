package com.example.safetynetalert.commons.pipeline_builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PreconditionsTest {

    @Test
    void throwsIfArgumentIsNull() {
        var msg = "Parameter must not be null";
        Throwable e =
            assertThrows(
                IllegalArgumentException.class,
                () -> Preconditions.isNotNull(null));

        assertThat(e).hasMessage(msg);
    }

    @Test
    void throwsReturnMessageIfArgumentIsNull() {
        var msg = "Shit has happened";
        Throwable e =
            assertThrows(
                IllegalArgumentException.class,
                () -> Preconditions.isNotNull(null, msg));

        assertThat(e).hasMessage(msg);
    }

    @Test
    void returnsAnArgumentWithoutMsgIfTheArgumentIsAValidObject() {
        // given
        Object checkedObject = new Object();

        // when
        Object returnedObject = Preconditions.isNotNull(checkedObject, "OK");

        // then
        assertThat(returnedObject).isEqualTo(checkedObject);
    }

    @Test
    void returnsAnArgumentIfTheArgumentIsAValidObject() {
        // given
        Object checkedObject = new Object();

        // when
        Object returnedObject = Preconditions.isNotNull(checkedObject);

        // then
        assertThat(returnedObject).isEqualTo(checkedObject);
    }
}
