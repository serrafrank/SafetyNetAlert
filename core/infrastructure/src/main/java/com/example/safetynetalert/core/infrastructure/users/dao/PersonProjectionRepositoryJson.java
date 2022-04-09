package com.example.safetynetalert.core.infrastructure.users.dao;

import com.example.safetynetalert.core.domain.persons.aggregate.Id;
import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;
import com.example.safetynetalert.core.domain.persons.query.ChildByAddressWithFamilyMembersValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirestationValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonProjectionRepository;
import com.example.safetynetalert.core.infrastructure.users.models.FirestationModel;
import com.example.safetynetalert.core.infrastructure.users.models.MedicalRecordModel;
import com.example.safetynetalert.core.infrastructure.users.models.PersonModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Set<PersonByFirestationValueObject> getPersonByFirestation(Integer stationNumber) {
        var addresses = this.dataStorage.getFirestationsByStationNumber(stationNumber)
            .map(FirestationModel::getAddress)
            .toList();

        return getPersonAggregates().filter(p -> addresses.contains(p.address()))
            .map(PersonByFirestationValueObject::new)
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
    public Set<String> getPersonPhoneNumbersByFirestationNumber(Integer stationNumber) {
        var address = dataStorage.getFirestationsByStationNumber(stationNumber)
            .map(FirestationModel::getAddress)
            .collect(Collectors.toSet());

        Set<String> phoneNumers = new HashSet<>();
        address.forEach(a -> phoneNumers.addAll(dataStorage.getPersonsByAddress(a)
            .map(PersonModel::getPhone)
            .collect(
                Collectors.toSet())));

        return phoneNumers;
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

        List<PersonAggregate> personAggregateList = new ArrayList<>();

        this.dataStorage
            .getPersonsByAddress(address)
            .forEach(p -> this.dataStorage.getMedicalRecordById(p.getId())
                .map(m -> createPersonAggregate(p, m))
                .ifPresent(
                    personAggregateList::add));

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
