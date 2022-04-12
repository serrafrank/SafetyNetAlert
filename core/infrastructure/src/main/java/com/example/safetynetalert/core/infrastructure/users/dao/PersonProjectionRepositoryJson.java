package com.example.safetynetalert.core.infrastructure.users.dao;

import com.example.safetynetalert.core.domain.persons.aggregate.Id;
import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;
import com.example.safetynetalert.core.domain.persons.query.*;
import com.example.safetynetalert.core.infrastructure.users.models.FireStationModel;
import com.example.safetynetalert.core.infrastructure.users.models.MedicalRecordModel;
import com.example.safetynetalert.core.infrastructure.users.models.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PersonProjectionRepositoryJson
        implements PersonProjectionRepository {

    private final DataStorage dataStorage;

    @Autowired
    public PersonProjectionRepositoryJson(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
            String firstname,
            String lastname) {

        var id = new Id(firstname,
                lastname);

        return getPersonAggregateById(id)
                .map(PersonByFirstnameAndLastnameValueObject::new);
    }

    @Override
    public Set<PersonByFireStationValueObject> getPersonByFireStation(Integer stationNumber) {
        var addresses = this.dataStorage.getFirestationsByStationNumber(stationNumber)
                .map(FireStationModel::getAddress)
                .toList();

        return getPersonAggregates().filter(p -> addresses.contains(p.address()))
                .map(PersonByFireStationValueObject::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ChildByAddressWithFamilyMembersValueObject> getChildernByAddressWithFamilyMembers(
            String address) {

        var persons = getPersonAggregateByAddress(address).collect(Collectors.toSet());

        if (persons.isEmpty()) {
            return Set.of();
        }

        var children = persons.stream().filter(PersonAggregate::isMinor);
        return children.map(c -> {
                    var family = persons.stream()
                            .filter(p -> !p.id().equals(c.id()))
                            .collect(Collectors.toSet());
                    return new ChildByAddressWithFamilyMembersValueObject(c, family);
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getPersonPhoneNumbersByFireStationNumber(Integer stationNumber) {
        var address = dataStorage.getFirestationsByStationNumber(stationNumber)
                .map(FireStationModel::getAddress)
                .collect(Collectors.toSet());

        Set<String> phoneNumbers = new HashSet<>();
        address.forEach(a -> phoneNumbers.addAll(dataStorage.getPersonsByAddress(a)
                .map(PersonModel::getPhone)
                .collect(
                        Collectors.toSet())));

        return phoneNumbers;
    }

    @Override
    public Set<PersonWithMedicalRecordsValueObject> getPersonsWithMedicalRecordByFireStationNumbersQuery(
            Set<Integer> stations) {

        var addresses = dataStorage.getFirestationsByStationNumbers(stations)
                .map(FireStationModel::getAddress)
                .collect(Collectors.toSet());

        return getPersonAggregateByAddresses(addresses)
                .map(PersonWithMedicalRecordsValueObject::new)
                .collect(Collectors.toSet());
    }

    private Optional<PersonAggregate> getPersonAggregateById(Id id) {
        var medicalRecord = this.dataStorage.getMedicalRecordById(id);
        var person = this.dataStorage.getPersonById(id);

        if (medicalRecord.isPresent() && person.isPresent()) {
            return Optional.of(createPersonAggregate(person.get(),
                    medicalRecord.get()));
        }

        return Optional.empty();
    }

    private Stream<PersonAggregate> getPersonAggregateByAddress(String address) {

        return getPersonAggregateByAddresses(Set.of(address));
    }

    private Stream<PersonAggregate> getPersonAggregateByAddresses(Set<String> address) {

        List<PersonAggregate> personAggregateList = new ArrayList<>();

        this.dataStorage
                .getPersonsByAddresses(address)
                .forEach(p -> this.dataStorage.getMedicalRecordById(p.getId())
                        .map(m -> createPersonAggregate(p, m))
                        .ifPresent(personAggregateList::add));

        return personAggregateList.stream();
    }

    private Stream<PersonAggregate> getPersonAggregates() {
        List<PersonAggregate> personAggregateList = new ArrayList<>();

        this.dataStorage.getPersons().forEach(p -> this.dataStorage.getMedicalRecordById(p.getId())
                .map(m -> createPersonAggregate(p, m))
                .ifPresent(
                        personAggregateList::add));

        return personAggregateList.stream();
    }

    private PersonAggregate createPersonAggregate(
            PersonModel p,
            MedicalRecordModel m) {

        return new PersonAggregate(p.getFirstName(),
                p.getLastName(),
                p.getAddress(),
                p.getCity(),
                p.getZip(),
                p.getPhone(),
                p.getEmail(),
                m.getBirthdate(),
                m.toMedicalRecord()
        );
    }
}
