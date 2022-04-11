package com.example.safetynetalert.core.presentation.io.output;

import com.example.safetynetalert.core.domain.persons.query.PersonWithMedicalRecordsValueObject;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersonWithMedicalRecordsResource {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private Integer age;

    @Getter
    private MedicalRecordResource medicalRecord;

    public PersonWithMedicalRecordsResource(PersonWithMedicalRecordsValueObject m) {
        this.firstName = m.firstName();
        this.lastName = m.lastName();
        this.age = m.age();
        this.medicalRecord = new MedicalRecordResource(m.medicalRecord());
    }

    @NoArgsConstructor
    public static class MedicalRecordResource {

        @Getter
        private List<String> medications;

        @Getter
        private List<String> allergies;

        public MedicalRecordResource(PersonWithMedicalRecordsValueObject.MedicalRecordValueObject medicalRecord) {
            this.medications = medicalRecord
                .medications()
                .stream()
                .map(Record::toString)
                .collect(Collectors.toList());
            this.allergies = medicalRecord.allergies();
        }

    }
}
