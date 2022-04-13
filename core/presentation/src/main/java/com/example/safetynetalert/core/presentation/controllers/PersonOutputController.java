package com.example.safetynetalert.core.presentation.controllers;

import static java.util.stream.Collectors.groupingBy;

import com.example.safetynetalert.commons.exception.GenericNotFoundException;
import com.example.safetynetalert.core.application.QueryUseCase;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonWithMedicalRecordsValueObject;
import com.example.safetynetalert.core.presentation.io.output.ChildByAddressWithFamilyMembersResponse;
import com.example.safetynetalert.core.presentation.io.output.PersonByFirstnameAndLastnameResponse;
import com.example.safetynetalert.core.presentation.io.output.PersonWithMedicalRecordsResponse;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonOutputController {

    private final QueryUseCase queryUseCaser;

    @Autowired
    public PersonOutputController(QueryUseCase queryUseCaser) {
        this.queryUseCaser = queryUseCaser;
    }

    @GetMapping("personInfo")
    public ResponseEntity<PersonByFirstnameAndLastnameResponse> getPersonInfo(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {

        PersonByFirstnameAndLastnameValueObject person = queryUseCaser
                .getPersonByFirstnameAndLastname(
                        firstName,
                        lastName)
                .orElseThrow(GenericNotFoundException::new);

        var resource = new PersonByFirstnameAndLastnameResponse(person);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("childAlert")
    public ResponseEntity<Set<ChildByAddressWithFamilyMembersResponse>> getChildrenByAddressWithFamilyMembers(@RequestParam("address") String address) {

        var resource = queryUseCaser.getChildrenByAddressWithFamilyMembers(address)
                .stream().map(ChildByAddressWithFamilyMembersResponse::new)
                .collect(Collectors.toSet());

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("flood/stations")
    public ResponseEntity<Map<String, Set<PersonWithMedicalRecordsResponse>>> getFamilyWithMedicalRecordByFirestationNumber(
            @RequestParam("stations") Set<Integer> stations) {

        Set<PersonWithMedicalRecordsValueObject> personsWithMedicalRecords = queryUseCaser.getPersonWithMedicalRecordsByFireStationNumber(
                stations);

        var resource = personsWithMedicalRecords.stream()
                .collect(groupingBy(PersonWithMedicalRecordsValueObject::address,
                        Collectors.mapping(PersonWithMedicalRecordsResponse::new,
                                Collectors.toSet())));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("communityEmail")
    public ResponseEntity<Set<String>> getEmailsByCity(@RequestParam("city") String city) {
        var resource = queryUseCaser.getEmailsByCity(city);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
