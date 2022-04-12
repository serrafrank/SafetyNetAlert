package com.example.safetynetalert.commons.pretty_validator;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class PrettyValidation {
    public static <T> PrettyValidationCondition<T> test(T argument) {
        return new PrettyValidationCondition<>(argument);
    }

    public static class PrettyValidationCondition<T> {
        protected final T argument;
        protected final List<Predicate<T>> predicates = new ArrayList<>();

        protected PrettyValidationCondition(T a, Predicate<T> p) {
            this.argument = a;
            this.predicates.add(p);
        }

        protected PrettyValidationCondition(T a) {
            this.argument = a;
        }

        public PrettyValidationResult<T> is(Predicate<T> p) {
            return new PrettyValidationResult<>(this.argument, p);
        }

        public PrettyValidationResult<T> isNot(Predicate<T> p) {
            return new PrettyValidationResult<>(argument, p.negate());
        }

        public PrettyValidationResult<T> isNull() {
            return is(Objects::isNull);
        }

        public PrettyValidationResult<T> isNotNull() {
            return isNot(Objects::isNull);
        }

        public PrettyValidationResult<T> isEmpty() {
            return is(ObjectUtils::isEmpty);
        }

        public PrettyValidationResult<T> isNotEmpty() {
            return isNot(ObjectUtils::isEmpty);
        }

    }

    public static class PrettyValidationResult<T> extends PrettyValidationCondition<T> {

        protected PrettyValidationResult(T a, Predicate<T> p){
            super(a, p);
        }

        public <TThrowable extends Throwable> boolean orThrow(Supplier<? extends TThrowable> exceptionSupplier)
                throws TThrowable {
            if (isNotValid()) {
                throw exceptionSupplier.get();
            }
            return true;
        }

        public boolean isValid() {
            return this.predicates.stream().allMatch(p -> p.test(argument));
        }

        public boolean isNotValid() {
            return !isValid();
        }
    }
}
