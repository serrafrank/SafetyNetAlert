package com.example.safetynetalert.core.application;

public interface CommandUseCase {

    void createPerson(String firstName,
                      String lastName,
                      String address,
                      String city,
                      String zip,
                      String phone,
                      String email);
    void editPerson();
    void deletePerson();

}
