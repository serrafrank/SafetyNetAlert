package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.query_pipeline.AbstractQueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetChildrenByAddressWithFamilyMembersQueryHandler
    extends AbstractQueryHandler<GetChildrenByAddressWithFamilyMembersQuery,
    Set<ChildByAddressWithFamilyMembersValueObject>> {

    private final PersonProjectionRepository personProjectionRepository;

    @Autowired
    public GetChildrenByAddressWithFamilyMembersQueryHandler(PersonProjectionRepository personProjectionRepository) {
        this.personProjectionRepository = personProjectionRepository;
    }

    @Override
    public Set<ChildByAddressWithFamilyMembersValueObject> handler(
        GetChildrenByAddressWithFamilyMembersQuery query) {
        return personProjectionRepository.getChildernByAddressWithFamilyMembers(query.address());
    }
}
