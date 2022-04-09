package com.example.safetynetalert.core.infrastructure.users.models;

import java.util.List;
import lombok.Getter;

@Getter
public class DataModel {

    private List<PersonModel> persons;
    private List<FirestationModel> firestations;
    private List<MedicalRecordModel> medicalrecords;
}
