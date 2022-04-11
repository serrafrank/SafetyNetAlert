package com.example.safetynetalert.core.domain.persons.aggregate;

public record Id(String id) {

    public Id(String... elements) {
        this(String.join(":", elements));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Id testerId = (Id) o;

        return id.equals(testerId.id());
    }
}
