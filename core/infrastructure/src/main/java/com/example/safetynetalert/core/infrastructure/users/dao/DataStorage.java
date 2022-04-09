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
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class DataStorage {

    private final DataModel data;

    @Value("${safe-net-alert.data-file}")
    private static final String DATA_FILE = "data.json";

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
            .filter(
                p -> p.getId().equals(id)
            )
            .findFirst();
    }

    public Stream<PersonModel> getPersonsByAddress(String address) {
        return data
            .getPersons()
            .stream()
            .filter(p -> p.getAddress().equals(address));
    }

    public Stream<FirestationModel> getFirestations() {
        return data
            .getFirestations()
            .stream();
    }

    public Stream<FirestationModel> getFirestationsByStationNumber(Integer stationNumber) {
        return getFirestations()
            .filter(f -> f.getStation().equals(stationNumber));
    }

    public Stream<FirestationModel> getFirestationsByAddress(String address) {
        return getFirestations()
            .filter(f -> f.getAddress().equals(address));
    }

    public Stream<MedicalRecordModel> getMedicalRecords() {
        return data
            .getMedicalrecords()
            .stream();
    }

    public Optional<MedicalRecordModel> getMedicalRecordById(Id id) {
        return getMedicalRecords()
            .filter(
                p -> p.getId().equals(id)
            )
            .findFirst();
    }
}
