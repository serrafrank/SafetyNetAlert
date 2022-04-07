package com.example.safteynetlert.domaine.persons.command;

import java.time.LocalDate;
import java.util.List;

public record PersonAggregate(String firstName,
                              String lastName,
                              String address,
                              String city,
                              String zip,
                              String phone,
                              String email,
                              LocalDate birthdate,
                              List<Medication> medications,
                              List<String> allergies) {


    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public void addMedication(String drug, String dose) {
        this.medications.add(new Medication(drug, dose));
    }

    public void addAllergie(String allergie) {
        this.allergies.add(allergie);
    }

}
