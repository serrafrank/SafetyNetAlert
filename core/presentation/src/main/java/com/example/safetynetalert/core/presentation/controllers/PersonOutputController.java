package com.example.safetynetalert.core.presentation.controllers;

import com.example.safetynetalert.core.application.QueryUseCase;
import com.example.safetynetalert.core.domain.exceptions.GenericNotFoundException;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonWithMedicalRecordsValueObject;
import com.example.safetynetalert.core.presentation.io.output.ChildByAddressWithFamilyMembersResourse;
import com.example.safetynetalert.core.presentation.io.output.PersonByFirstnameAndLastnameResource;
import com.example.safetynetalert.core.presentation.io.output.PersonWithMedicalRecordsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RestController
public class PersonOutputController {

    private final QueryUseCase queryUseCaser;

    @Autowired
    public PersonOutputController(QueryUseCase queryUseCaser) {
        this.queryUseCaser = queryUseCaser;
    }

    @GetMapping("personInfo")
    public PersonByFirstnameAndLastnameResource getPersonInfo(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {

        PersonByFirstnameAndLastnameValueObject person = queryUseCaser
                .getPersonByFirstnameAndLastname(
                        firstName,
                        lastName)
                .orElseThrow(GenericNotFoundException::new);

        return new PersonByFirstnameAndLastnameResource(person);
    }

    @GetMapping("childAlert")
    public Set<ChildByAddressWithFamilyMembersResourse> getChildrenByAddressWithFamilyMembers(@RequestParam("address") String address) {

        return queryUseCaser.getChildrenByAddressWithFamilyMembers(address)
                            .stream().map(ChildByAddressWithFamilyMembersResourse::new)
                            .collect(Collectors.toSet());
    }

    @GetMapping("flood/stations")
    public Map<String, Set<PersonWithMedicalRecordsResource>> getFamilyWithMedicalRecordByFirestationNumber(
            @RequestParam("stations") Set<Integer> stations) {

        Set<PersonWithMedicalRecordsValueObject> personsWithMedicalRecords = queryUseCaser.getPersonWithMedicalRecordsByFirestationNumber(
                stations);

        return personsWithMedicalRecords.stream()
                                        .collect(groupingBy(PersonWithMedicalRecordsValueObject::address,
                                                Collectors.mapping(PersonWithMedicalRecordsResource::new,
                                                        Collectors.toSet())));
    }
}
