package com.example.safteynetlert.infrastructure.dao;

import com.example.safteynetlert.domaine.persons.command.Id;
import com.example.safteynetlert.domaine.persons.command.PersonAggregate;
import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safteynetlert.domaine.persons.query.PersonProjectionRepository;
import com.example.safteynetlert.infrastructure.models.MedicalRecordModel;
import com.example.safteynetlert.infrastructure.models.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class PersonProjectionRepositoryJson implements PersonProjectionRepository {

    @Autowired
    private DataStorage dataStorage;

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> GetPersonByFirstnameAndLastname(
            String firstname,
            String lastname) {

        var id = new Id(firstname,
                        lastname);

        return getPersonAggregateById(id)
                .map(PersonByFirstnameAndLastnameValueObject::new);
    }

    private Optional<PersonAggregate> getPersonAggregateById(Id id) {
        var medicalRecord = getMedicalRecordById(id);
        var person = getPersonById(id);

        if (medicalRecord.isPresent() && person.isPresent()) {
            return Optional.of(createPersonAggregate(person.get(),
                                                     medicalRecord.get()));
        }

        return Optional.empty();
    }

    private Stream<PersonAggregate> getPersonAggregates() {
        List<PersonAggregate> personAggregateList = new ArrayList<>();

        getPersons().forEach(p -> getMedicalRecordById(p.getId())
                .map(m -> createPersonAggregate(p, m))
                .ifPresent(
                        personAggregateList::add));

        return personAggregateList.stream();
    }

    private Stream<MedicalRecordModel> getMedicalRecords() {
        return dataStorage.getData()
                .getMedicalrecords()
                .stream();
    }

    private Stream<PersonModel> getPersons() {
        return dataStorage.getData()
                .getPersons()
                .stream();
    }

    private Optional<MedicalRecordModel> getMedicalRecordById(Id id) {
        return getMedicalRecords()
                .filter(
                        p -> p.getId().equals(id)
                )
                .findFirst();
    }

    private Optional<PersonModel> getPersonById(Id id) {
        return getPersons()
                .filter(
                        p -> p.getId().equals(id)
                )
                .findFirst();
    }

    private PersonAggregate createPersonAggregate(PersonModel p,
                                                  MedicalRecordModel m) {

        return new PersonAggregate(p.getFirstName(),
                                   p.getLastName(),
                                   p.getAddress(),
                                   p.getCity(),
                                   p.getZip(),
                                   p.getPhone(),
                                   p.getEmail(),
                                   m.getBirthdate(),
                                   m.getMedications(),
                                   m.getAllergies()
        );
    }
}
