package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.domaine.persons.command.Medication;
import com.example.safteynetlert.infrastructure.models.MedicalRecord;
import com.example.safteynetlert.infrastructure.models.Person;

import java.time.LocalDate;
import java.util.List;

public record PersonByFirstnameAndLastnameValueObject (
        String firstName,
        String lastName,
        String address,
        String city,
        String zip,
        String email,
        Integer age,
        List<Medication> medications,
        List<String> allergies) {


    public PersonByFirstnameAndLastnameValueObject(Person p, MedicalRecord m) {

        this(p.getFirstName(),
             p.getLastName(),
             p.getAddress(),
             p.getCity(),
             p.getZip(),
             p.getEmail(),
             m.getAge(),
             m.getMedications(),
             m.getAllergies());
    }
}
