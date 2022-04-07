package com.example.safteynetlert.infrastructure.models;

import lombok.Getter;

@Getter
public class Person {

    private final Id id;
    private final String address;
    private final String city;
    private final String zip;
    private final String phone;
    private final String email;

    public Person(String firstName,
                  String lastName,
                  String address,
                  String city,
                  String zip,
                  String phone,
                  String email) {
        this.id = new Id(firstName, lastName);
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return id.getFirstName();
    }

    public String getLastName() {
        return id.getLastName();
    }
}