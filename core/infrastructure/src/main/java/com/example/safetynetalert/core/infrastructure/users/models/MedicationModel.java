package com.example.safetynetalert.core.infrastructure.users.models;

import com.example.safetynetalert.core.domain.persons.aggregate.Medication;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MedicationModel {

    @Getter
    private String drug;

    @Getter
    private String dose;

    public MedicationModel(String medication) {
        var meds = medication.split(":");
        this.drug = meds[0];
        this.dose = meds[1];
    }

    public Medication toMedication() {
        return new Medication(drug, dose);
    }
}
