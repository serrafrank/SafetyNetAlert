package com.example.safteynetlert.application;

import com.example.safteynetlert.application.core.query_pipeline.QueryBus;
import com.example.safteynetlert.domaine.persons.query.ChildByAddressWithFamilyMembersValueObject;
import com.example.safteynetlert.domaine.persons.query.GetChildrenByAddressWithFamilyMembersQuery;
import com.example.safteynetlert.domaine.persons.query.GetPersonByFirestationQuery;
import com.example.safteynetlert.domaine.persons.query.GetPersonByFirstnameAndLastnameQuery;
import com.example.safteynetlert.domaine.persons.query.GetPersonPhoneNumbersByFirestationNumberQuery;
import com.example.safteynetlert.domaine.persons.query.PersonByFirestationValueObject;
import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;
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
    public Set<PersonByFirestationValueObject> getPersonsByFirestation(Integer firestationNubmer) {
        return queryBus.dispatch(new GetPersonByFirestationQuery(firestationNubmer));
    }

    @Override
    public Set<ChildByAddressWithFamilyMembersValueObject> getChildrenByAddressWithFamilyMembers(
        String address) {
        return queryBus.dispatch(new GetChildrenByAddressWithFamilyMembersQuery(address));
    }

    @Override
    public Set<String> getPersonPhoneNumbersByFirestationNumber(Integer firestationNubmer) {
        return queryBus.dispatch(new GetPersonPhoneNumbersByFirestationNumberQuery(firestationNubmer));
    }
}
