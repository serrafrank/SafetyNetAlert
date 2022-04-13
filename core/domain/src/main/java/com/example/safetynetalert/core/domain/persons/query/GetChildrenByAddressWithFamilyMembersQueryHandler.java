package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryHandler;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetChildrenByAddressWithFamilyMembersQueryHandler
    implements QueryHandler<GetChildrenByAddressWithFamilyMembersQuery,
    Set<ChildByAddressWithFamilyMembersValueObject>> {

    private final PersonProjectionRepository repository;

    @Autowired
    public GetChildrenByAddressWithFamilyMembersQueryHandler(PersonProjectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<ChildByAddressWithFamilyMembersValueObject> handler(
            GetChildrenByAddressWithFamilyMembersQuery query) {
        return repository.getChildrenByAddressWithFamilyMembers(query.getAddress());
    }
}
