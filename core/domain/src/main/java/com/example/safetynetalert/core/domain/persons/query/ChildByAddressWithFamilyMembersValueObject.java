package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record ChildByAddressWithFamilyMembersValueObject(
        String firstName,
        String lastName,
        Integer age,
        Set<FamilyMember> familyMembers
) {

    public ChildByAddressWithFamilyMembersValueObject {
        if (familyContentChild(firstName, lastName, familyMembers)) {
            throw new IllegalArgumentException("Family could not content child");
        }
    }

    public ChildByAddressWithFamilyMembersValueObject(
            PersonAggregate p,
            Collection<PersonAggregate> family) {
        this(p.firstName(),
                p.lastName(),
                p.age(),
                family.stream().map(FamilyMember::new).collect(Collectors.toSet())
        );
    }

    private boolean familyContentChild(
            String firstName,
            String lastName,
            Set<FamilyMember> familyMembers) {
        return familyMembers.stream()
                .anyMatch(f -> f.firstName().equals(firstName) && f.lastName().equals(lastName));
    }

    public record FamilyMember(String firstName,
                               String lastName,
                               Integer age) {

        FamilyMember(PersonAggregate m) {
            this(m.firstName(),
                    m.lastName(),
                    m.age());
        }
    }
}


