package com.example.safetynetalert.core.application;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryBus;
import com.example.safetynetalert.core.domain.persons.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
    public Set<PersonByFireStationValueObject> getPersonsByFireStation(Integer station) {
        return queryBus.dispatch(new GetPersonByFireStationQuery(station));
    }

    @Override
    public Set<ChildByAddressWithFamilyMembersValueObject> getChildrenByAddressWithFamilyMembers(
            String address) {
        return queryBus.dispatch(new GetChildrenByAddressWithFamilyMembersQuery(address));
    }

    @Override
    public Set<String> getPersonPhoneNumbersByFireStationNumber(Integer station) {
        return queryBus.dispatch(new GetPersonPhoneNumbersByFireStationNumberQuery(station));
    }

    @Override
    public Set<PersonWithMedicalRecordsValueObject> getPersonWithMedicalRecordsByFireStationNumber(
            Set<Integer> stations) {
        return queryBus.dispatch(new GetPersonsWithMedicalRecordByFireStationNumbersQuery(stations));
    }
}
