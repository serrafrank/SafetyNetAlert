package com.example.safetynetalert.core.presentation.io.input;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CreateMedicalRecordResource {
    @Getter
    private final List<String> medications = new ArrayList<>();
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

    @JsonSetter("birthdate")
    public void setBirthdate(String date) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.birthdate = LocalDate.parse(date, formatter);
    }

    @JsonSetter("medications")
    public void addMedications(List<String> medications) {
        this.medications.addAll(medications);
    }

    @JsonSetter("allergies")
    public void addAllergies(List<String> allergies) {
        this.allergies.addAll(allergies);
    }

}
