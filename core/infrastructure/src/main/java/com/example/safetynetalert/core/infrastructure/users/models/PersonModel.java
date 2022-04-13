package com.example.safetynetalert.core.infrastructure.users.models;

import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PersonModel {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String zip;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String email;

    public PersonAggregate.Id getId() {
        return new PersonAggregate.Id(firstName, lastName);
    }

    public PersonModel(PersonAggregate p) {
        this.firstName = p.firstName();
        this.lastName = p.lastName();
        this.address = p.address();
        this.city = p.city();
        this.zip = p.zip();
        this.phone = p.phone();
        this.email = p.email();
    }
}
