package com.example.safteynetlert.infrastructure.models;

import com.example.safteynetlert.domaine.persons.command.Medication;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MedicalRecord {

    private final Id id;
    private final LocalDate birthdate;
    private final List<Medication> medications = new ArrayList<>();
    private final List<String> allergies = new ArrayList<>();

    public MedicalRecord(String firstName,
                         String lastName,
                         LocalDate birthdate,
                         List<Medication> medications,
                         List<String> allergies) {
        this.id = new Id(firstName, lastName);
        this.birthdate = birthdate;
        this.medications.addAll(medications);
        this.allergies.addAll(allergies);
    }

    public Integer getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }
}
