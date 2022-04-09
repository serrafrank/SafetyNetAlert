package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.application.core.query_pipeline.Query;

public record GetChildrenByAddressWithFamilyMembersQuery(String address)
    implements Query {

}
