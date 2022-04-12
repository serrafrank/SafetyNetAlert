package com.example.safetynetalert.core.presentation.io.output;

import com.example.safetynetalert.core.domain.persons.query.PersonByFireStationValueObject;
import lombok.Getter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AllPersonsByFireStationNumberResource {

    @Getter
    private final Set<PersonByFireStationNumberDto> persons;

    @Getter
    private final Long numberOfChildren;

    public AllPersonsByFireStationNumberResource(Collection<PersonByFireStationValueObject> personsByFireStationNumber) {
        persons = personsByFireStationNumber.stream()
                .map(PersonByFireStationNumberDto::new)
                .collect(
                        Collectors.toSet());

        numberOfChildren = personsByFireStationNumber.stream()
                .filter(PersonByFireStationValueObject::isMinor)
                .count();
    }

    static class PersonByFireStationNumberDto {

        @Getter
        private final String firstName;

        @Getter
        private final String lastName;

        @Getter
        private final String address;

        @Getter
        private final String city;

        @Getter
        private final String zip;

        @Getter
        private final String phone;

        public PersonByFireStationNumberDto(PersonByFireStationValueObject person) {
            this.firstName = person.firstName();
            this.lastName = person.lastName();
            this.address = person.address();
            this.city = person.city();
            this.zip = person.zip();
            this.phone = person.phone();
        }
    }
}
