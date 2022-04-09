package com.example.safetynetalert.core.application;

import com.example.safetynetalert.core.domain.persons.query.ChildByAddressWithFamilyMembersValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirestationValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirstnameAndLastnameValueObject;
import java.util.Optional;
import java.util.Set;

public interface QueryUseCase {

    Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
        String firstname,
        String lastname);

    Set<PersonByFirestationValueObject> getPersonsByFirestation(Integer firestationNubmer);

    Set<ChildByAddressWithFamilyMembersValueObject> getChildrenByAddressWithFamilyMembers(String address);

    Set<String> getPersonPhoneNumbersByFirestationNumber(Integer firestationNubmer);
}
