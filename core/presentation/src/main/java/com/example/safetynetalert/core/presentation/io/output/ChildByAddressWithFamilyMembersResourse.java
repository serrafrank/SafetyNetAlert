package com.example.safetynetalert.core.presentation.io.output;

import com.example.safetynetalert.core.domain.persons.query.ChildByAddressWithFamilyMembersValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class ChildByAddressWithFamilyMembersResourse {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private Integer age;

    @Getter
    private Set<FamilyMember> familyMembers;

    public ChildByAddressWithFamilyMembersResourse(ChildByAddressWithFamilyMembersValueObject c) {
        this.firstName = c.firstName();
        this.lastName = c.lastName();
        this.age = c.age();
        this.familyMembers = c.familyMembers().stream()
                .map(FamilyMember::new)
                .collect(Collectors.toSet());
    }

    @NoArgsConstructor
    private static final class FamilyMember {

        @Getter
        private String firstName;

        @Getter
        private String lastName;

        @Getter
        private Integer age;

        public FamilyMember(ChildByAddressWithFamilyMembersValueObject.FamilyMember m) {
            this.firstName = m.firstName();
            this.lastName = m.lastName();
            this.age = m.age();
        }
    }
}

