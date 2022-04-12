package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.AbstractQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GetChildrenByAddressWithFamilyMembersQueryHandler
        extends AbstractQueryHandler<GetChildrenByAddressWithFamilyMembersQuery,
        Set<ChildByAddressWithFamilyMembersValueObject>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetChildrenByAddressWithFamilyMembersQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<ChildByAddressWithFamilyMembersValueObject> handler(
            GetChildrenByAddressWithFamilyMembersQuery query) {
        return repository.getChildernByAddressWithFamilyMembers(query.address());
    }
}
