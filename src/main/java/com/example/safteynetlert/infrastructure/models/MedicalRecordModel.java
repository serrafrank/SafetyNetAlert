package com.example.safteynetlert.infrastructure.models;

import com.example.safteynetlert.domaine.persons.command.Id;
import com.example.safteynetlert.domaine.persons.command.Medication;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class MedicalRecordModel {

    private final List<MedicationModel> medications = new ArrayList<>();
    @Getter
    private final List<String> allergies = new ArrayList<>();
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    private LocalDate birthdate;

    public Id getId() {
        return new Id(firstName, lastName);
    }

    public Integer getAge() {
        return Period.between(birthdate, LocalDate.now())
                .getYears();
    }

    @JsonSetter("birthdate")
    public void setBirthdate(String date) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.birthdate = LocalDate.parse(date, formatter);
    }

    @JsonSetter("medications")
    public void addMedications(List<String> medications) {
        this.medications.addAll(medications
                                        .stream().map(
                        MedicationModel::new)
                                        .collect(Collectors.toList()));
    }

    public List<Medication> getMedications() {
        return medications.stream()
                .map(MedicationModel::toMedication)
                .collect(
                        Collectors.toList());
    }

    @JsonSetter("allergies")
    public void addAllergies(List<String> allergies) {
        this.allergies.addAll(allergies);
    }
}
