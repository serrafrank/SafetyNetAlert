package com.example.safteynetlert.application;

import com.example.safteynetlert.domaine.persons.query.ChildByAddressWithFamilyMembersValueObject;
import com.example.safteynetlert.domaine.persons.query.PersonByFirestationValueObject;
import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;

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
