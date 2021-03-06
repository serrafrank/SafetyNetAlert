package com.example.safetynetalert.core.domain.persons.query;

import java.util.Optional;
import java.util.Set;

public interface PersonProjectionRepository {

    Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
        String firstname,
        String lastname);

    Set<PersonByFirestationValueObject> getPersonByFirestation(Integer stationNumber);

    Set<ChildByAddressWithFamilyMembersValueObject> getChildernByAddressWithFamilyMembers(String address);

    Set<String> getPersonPhoneNumbersByFirestationNumber(Integer stationNumber);

    Set<PersonWithMedicalRecordsValueObject> getPersonsWithMedicalRecordByFirestationNumbersQuery(
        Set<Integer> stations);
}
