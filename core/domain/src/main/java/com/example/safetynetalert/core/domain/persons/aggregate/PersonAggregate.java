package com.example.safetynetalert.core.domain.persons.aggregate;

import java.time.LocalDate;
import java.time.Period;

public record PersonAggregate(
    String firstName,
    String lastName,
    String address,
    String city,
    String zip,
    String phone,
    String email,
    LocalDate birthdate,
    MedicalRecord medicalRecord) {

    public Id id() {
        return new Id(firstName, lastName);
    }

    public Integer age() {
        return Period.between(birthdate, LocalDate.now())
            .getYears();
    }

    public void addMedication(Medication medication) {
        this.medicalRecord.medications().add(medication);
    }

    public void addMedication(String drug, String dose) {
        this.medicalRecord.medications().add(new Medication(drug, dose));
    }

    public void addAllergie(String allergie) {
        this.medicalRecord.allergies().add(allergie);
    }

    public boolean isMinor() {
        return age() <= 18;
    }
}
