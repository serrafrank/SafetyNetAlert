package com.example.safteynetlert.domaine.persons;

import com.example.safteynetlert.infrastructure.models.Person;
import com.example.safteynetlert.presentation.io.input.CreateMedicalRecord;
import com.example.safteynetlert.presentation.io.input.CreatePerson;

public interface PersonDomain {
    void createPerson(CreatePerson createPerson);
    void addMedicalRecords(CreateMedicalRecord medicalRecord);

    Person getPersonInfo(String firstName, String lastName);
}
