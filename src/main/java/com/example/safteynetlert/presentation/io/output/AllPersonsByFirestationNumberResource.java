package com.example.safteynetlert.presentation.io.output;

import com.example.safteynetlert.domaine.persons.query.PersonByFirestationValueObject;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

public class AllPersonsByFirestationNumberResource {
    @Getter
    private final Set<PersonByFirestationNumberDto> persons;

    @Getter
    private final Long numberOfChildren;

    public AllPersonsByFirestationNumberResource(Collection<PersonByFirestationValueObject> personsByFirestationNumber) {
        persons = personsByFirestationNumber.stream()
            .map(PersonByFirestationNumberDto::new)
            .collect(
                Collectors.toSet());

        numberOfChildren = personsByFirestationNumber.stream().filter(PersonByFirestationValueObject::isMinor).count();
    }

    static class PersonByFirestationNumberDto {

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

        public PersonByFirestationNumberDto(PersonByFirestationValueObject person) {
            this.firstName = person.firstName();
            this.lastName = person.lastName();
            this.address = person.address();
            this.city = person.city();
            this.zip = person.zip();
            this.phone = person.phone();
        }
    }

}
