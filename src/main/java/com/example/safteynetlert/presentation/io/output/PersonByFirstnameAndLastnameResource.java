package com.example.safteynetlert.presentation.io.output;

import com.example.safteynetlert.domaine.persons.command.Medication;
import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class PersonByFirstnameAndLastnameResource {

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final String address;

    @Getter
    private final String city;

    @Getter
    private final String zip;

    @Getter
    private final String email;

    @Getter
    private final Integer age;

    @Getter
    private final List<String> medications;

    @Getter
    private final List<String> allergies;

    public PersonByFirstnameAndLastnameResource(PersonByFirstnameAndLastnameValueObject person) {

        this.firstName = person.firstName();
        this.lastName = person.lastName();
        this.address = person.address();
        this.city = person.city();
        this.zip = person.zip();
        this.email = person.email();
        this.age = person.age();
        this.medications = person.medications()
                .stream()
                .map(Medication::toString)
                .collect(Collectors.toList());
        this.allergies = person.allergies();
    }
}
