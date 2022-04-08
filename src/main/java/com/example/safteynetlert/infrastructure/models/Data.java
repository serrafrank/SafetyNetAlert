package com.example.safteynetlert.infrastructure.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Data {
    private List<PersonModel> persons;
    private List<FirestationModel> firestations;
    private List<MedicalRecordModel> medicalrecords;
}