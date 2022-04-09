package com.example.safetynetalert.core.domain.persons.aggregate;

public record Medication(
    String drug,
    String dose) {

    @Override
    public String toString() {
        return drug + ":" + dose;
    }
}
