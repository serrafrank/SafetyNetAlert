package com.example.safetynetalert.core.domain.persons.query;

import java.util.Optional;
import java.util.Set;

public interface PersonProjectionRepository {

    Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
            String firstname,
            String lastname);

    Set<PersonByFireStationValueObject> getPersonByFireStation(Integer stationNumber);

    Set<ChildByAddressWithFamilyMembersValueObject> getChildernByAddressWithFamilyMembers(String address);

    Set<String> getPersonPhoneNumbersByFireStationNumber(Integer stationNumber);

    Set<PersonWithMedicalRecordsValueObject> getPersonsWithMedicalRecordByFireStationNumbersQuery(
            Set<Integer> stations);
}
