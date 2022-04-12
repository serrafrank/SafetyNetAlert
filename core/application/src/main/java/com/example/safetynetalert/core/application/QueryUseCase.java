package com.example.safetynetalert.core.application;

import com.example.safetynetalert.core.domain.persons.query.ChildByAddressWithFamilyMembersValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonByFireStationValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonWithMedicalRecordsValueObject;

import java.util.Optional;
import java.util.Set;

public interface QueryUseCase {

    Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
            String firstname,
            String lastname);

    Set<PersonByFireStationValueObject> getPersonsByFireStation(Integer station);

    Set<ChildByAddressWithFamilyMembersValueObject> getChildrenByAddressWithFamilyMembers(String address);

    Set<String> getPersonPhoneNumbersByFireStationNumber(Integer station);

    Set<PersonWithMedicalRecordsValueObject> getPersonWithMedicalRecordsByFireStationNumber(Set<Integer> stations);
}
