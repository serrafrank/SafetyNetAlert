package com.example.safetynetalert.core.infrastructure.users.models;

import lombok.Getter;

import java.util.List;

@Getter
public class DataModel {

    private List<PersonModel> persons;
    private List<FireStationModel> firestations;
    private List<MedicalRecordModel> medicalrecords;
}
