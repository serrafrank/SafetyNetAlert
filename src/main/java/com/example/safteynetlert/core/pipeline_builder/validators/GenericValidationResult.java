package com.example.safteynetlert.core.pipeline_builder.validators;

public record GenericValidationResult(boolean isValid) {

    public static GenericValidationResult ok() {
        return new GenericValidationResult(true);
    }

    public static GenericValidationResult fail() {
        return new GenericValidationResult(false);
    }

    public <TThrowable extends Throwable> boolean orThrow(Supplier<? extends TThrowable> exceptionSupplier)
        throws TThrowable {
        if (!isValid) {
            throw exceptionSupplier.get();
        }
        return true;
    }
}
