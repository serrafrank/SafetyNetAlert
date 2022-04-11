package com.example.safetynetalert.core.infrastructure.users.dao;

import com.example.safetynetalert.core.domain.persons.aggregate.Id;
import com.example.safetynetalert.core.infrastructure.users.models.DataModel;
import com.example.safetynetalert.core.infrastructure.users.models.FirestationModel;
import com.example.safetynetalert.core.infrastructure.users.models.MedicalRecordModel;
import com.example.safetynetalert.core.infrastructure.users.models.PersonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class DataStorage {

    @Value("${safe-net-alert.data-file}")
    private static final String DATA_FILE = "data.json";
    private final DataModel data;

    public DataStorage() throws IOException {

        if (StringUtils.isEmpty(DATA_FILE)) {
            throw new IllegalArgumentException(
                "safe-net-alert.data-file cant be null or " +
                "empty");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new ClassPathResource(DATA_FILE).getFile();
        this.data = objectMapper.readValue(file, DataModel.class);
    }

    public Stream<PersonModel> getPersons() {
        return data
            .getPersons()
            .stream();
    }

    public Optional<PersonModel> getPersonById(Id id) {
        return getPersons()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }

    public Stream<PersonModel> getPersonsByAddress(String address) {
        return getPersonsByAddresses(Set.of(address));
    }

    public Stream<PersonModel> getPersonsByAddresses(Set<String> addresses) {
        return data
            .getPersons()
            .stream()
            .filter(p -> addresses.contains(p.getAddress()));
    }

    public Stream<FirestationModel> getFirestations() {
        return data
            .getFirestations()
            .stream();
    }

    public Stream<FirestationModel> getFirestationsByStationNumber(Integer stationNumber) {
        return getFirestationsByStationNumbers(Set.of(stationNumber));
    }

    public Stream<FirestationModel> getFirestationsByStationNumbers(Set<Integer> stationNumbers) {
        return getFirestations()
            .filter(f -> stationNumbers.contains(f.getStation()));
    }

    public Stream<FirestationModel> getFirestationsByAddress(String address) {
        return getFirestationsByAddresses(Set.of(address));
    }

    public Stream<FirestationModel> getFirestationsByAddresses(Set<String> address) {
        return getFirestations()
            .filter(f -> address.contains(f.getAddress()));
    }

    public Stream<MedicalRecordModel> getMedicalRecords() {
        return data
            .getMedicalrecords()
            .stream();
    }

    public Optional<MedicalRecordModel> getMedicalRecordById(Id id) {
        return getMedicalRecords()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }
}
