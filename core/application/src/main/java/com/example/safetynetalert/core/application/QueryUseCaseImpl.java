package com.example.safetynetalert.core.application;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryBus;
import com.example.safetynetalert.core.domain.persons.query.ChildByAddressWithFamilyMembersValueObject;
import com.example.safetynetalert.core.domain.persons.query.GetChildrenByAddressWithFamilyMembersQuery;
import com.example.safetynetalert.core.domain.persons.query.GetPersonByFirestationQuery;
import com.example.safetynetalert.core.domain.persons.query.GetPersonByFirstnameAndLastnameQuery;
import com.example.safetynetalert.core.domain.persons.query.GetPersonPhoneNumbersByFirestationNumberQuery;
import com.example.safetynetalert.core.domain.persons.query.GetPersonsWithMedicalRecordByFirestationNumbersQuery;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirestationValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safetynetalert.core.domain.persons.query.PersonWithMedicalRecordsValueObject;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryUseCaseImpl
    implements QueryUseCase {

    private final QueryBus queryBus;

    @Autowired
    public QueryUseCaseImpl(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @Override
    public Optional<PersonByFirstnameAndLastnameValueObject> getPersonByFirstnameAndLastname(
        String firstname,
        String lastname) {
        return queryBus.dispatch(new GetPersonByFirstnameAndLastnameQuery(
            firstname,
            lastname));
    }

    @Override
    public Set<PersonByFirestationValueObject> getPersonsByFirestation(Integer station) {
        return queryBus.dispatch(new GetPersonByFirestationQuery(station));
    }

    @Override
    public Set<ChildByAddressWithFamilyMembersValueObject> getChildrenByAddressWithFamilyMembers(
        String address) {
        return queryBus.dispatch(new GetChildrenByAddressWithFamilyMembersQuery(address));
    }

    @Override
    public Set<String> getPersonPhoneNumbersByFirestationNumber(Integer station) {
        return queryBus.dispatch(new GetPersonPhoneNumbersByFirestationNumberQuery(station));
    }

    @Override
    public Set<PersonWithMedicalRecordsValueObject> getPersonWithMedicalRecordsByFirestationNumber(
        Set<Integer> stations) {
        return queryBus.dispatch(new GetPersonsWithMedicalRecordByFirestationNumbersQuery(stations));
    }
}
