package com.example.safetynetalert.commons.pipeline_builder.validators;

import java.util.function.Supplier;

public record GenericValidationResult(boolean isValid) {

    public boolean isNotValid() {
        return !isValid;
    }

    public <TThrowable extends Throwable> boolean orThrow(Supplier<? extends TThrowable> exceptionSupplier)
        throws TThrowable {
        if (isNotValid()) {
            throw exceptionSupplier.get();
        }
        return true;
    }
}
