package com.example.safteynetlert.infrastructure.dao;

import com.example.safteynetlert.domaine.persons.command.Id;
import com.example.safteynetlert.infrastructure.models.Data;
import com.example.safteynetlert.infrastructure.models.FirestationModel;
import com.example.safteynetlert.infrastructure.models.MedicalRecordModel;
import com.example.safteynetlert.infrastructure.models.PersonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class DataStorage {
    private final Data data;

    @Value("${safe-net-alert.data-file}")
    private String dataFile = "data.json";

    public DataStorage() throws IOException {

        if (StringUtils.isEmpty(dataFile)) {
            throw new IllegalArgumentException(
                    "safe-net-alert.data-file cant be null or " +
                            "empty");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new ClassPathResource(this.dataFile).getFile();
        this.data = objectMapper.readValue(file, Data.class);
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

    public Stream<FirestationModel> getFirestations(){
        return data
            .getFirestations()
            .stream();
    }

    public Stream<FirestationModel> getFirestationsByStationNumber(Integer stationNumber){
        return getFirestations()
            .filter(f -> f.getStation().equals(stationNumber) );
    }

    public Stream<FirestationModel> getFirestationsByAddress(String address){
        return getFirestations()
            .filter(f -> f.getAddress().equals(address) );
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
