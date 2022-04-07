package com.example.safteynetlert.presentation.io.input;

import com.example.safteynetlert.domaine.persons.command.Medication;

import java.time.LocalDate;
import java.util.List;

public class CreateMedicalRecord {

    private final String firstName;
    private final String lastName;
    private final LocalDate birthdate;
    private final List<Medication> medications;
    private final List<String> allergies;

    public CreateMedicalRecord(String firstName,
                               String lastName,
                               LocalDate birthdate,
                               List<Medication> medications,
                               List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }
}
