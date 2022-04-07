package com.example.safteynetlert.infrastructure.dao;

import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safteynetlert.domaine.persons.query.PersonProjectionRepository;
import com.example.safteynetlert.infrastructure.models.Id;
import com.example.safteynetlert.infrastructure.models.MedicalRecord;
import com.example.safteynetlert.infrastructure.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

        return getPersonsById(id)
                .map(p ->
                    getMedicalRecordsById(id).map(
                            m -> new PersonByFirstnameAndLastnameValueObject(p, m)
                    )
                )
                .findFirst();
    }


    private Stream<Person> getPersonsById(Id id){
        return dataStorage.getData()
                .getPersons()
                .stream()
                .filter(
                        p -> p.getId().equals(id)
                );
    }

    private Stream<MedicalRecord> getMedicalRecordsById(Id id){
        return dataStorage.getData()
                .getMedicalrecords()
                .stream()
                .filter(
                        p -> p.getId().equals(id)
                );
    }
}
