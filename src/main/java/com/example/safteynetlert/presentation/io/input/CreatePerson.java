package com.example.safteynetlert.presentation.io.input;

public class CreatePerson {

    private final String firstName;
    private final String lastName;
    private final String city;
    private final String zip;
    private final String phone;
    private final String email;

    public CreatePerson(String firstName,
                        String lastName,
                        String city,
                        String zip,
                        String phone,
                        String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
