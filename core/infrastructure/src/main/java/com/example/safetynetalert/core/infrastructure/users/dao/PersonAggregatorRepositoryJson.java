package com.example.safetynetalert.core.infrastructure.users.dao;

import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;
import com.example.safetynetalert.core.domain.persons.command.PersonAggregatorRepository;
import com.example.safetynetalert.core.infrastructure.users.models.MedicalRecordModel;
import com.example.safetynetalert.core.infrastructure.users.models.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonAggregatorRepositoryJson
    implements PersonAggregatorRepository {

    private final DataStorage dataStorage;

    @Autowired
    public PersonAggregatorRepositoryJson(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public void createPerson(PersonAggregate personAggregate) {
        PersonModel personModel = new PersonModel(personAggregate);
        MedicalRecordModel medicalRecordModel = new MedicalRecordModel(personAggregate);
        dataStorage.addPerson(personModel);
        dataStorage.addMedicalRecords(medicalRecordModel);
    }
}
