package com.example.safetynetalert.core.infrastructure.users.models;

import com.example.safetynetalert.core.domain.persons.aggregate.Id;
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

    public Id getId() {
        return new Id(firstName, lastName);
    }

}
