package com.example.safteynetlert.infrastructure.models;

import lombok.Getter;

import java.util.List;

@Getter
public class Data {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;
}