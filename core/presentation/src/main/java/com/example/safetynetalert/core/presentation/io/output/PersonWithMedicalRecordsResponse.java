package com.example.safetynetalert.core.presentation.io.output;

import com.example.safetynetalert.core.domain.persons.query.PersonWithMedicalRecordsValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PersonWithMedicalRecordsResponse {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private Integer age;

    @Getter
    private MedicalRecordResource medicalRecord;

    public PersonWithMedicalRecordsResponse(PersonWithMedicalRecordsValueObject m) {
        this.firstName = m.firstName();
        this.lastName = m.lastName();
        this.age = m.age();
        this.medicalRecord = new MedicalRecordResource(m.medicalRecord());
    }

    @NoArgsConstructor
    public static class MedicalRecordResource {

        private List<MedicationResource> medications;

        @Getter
        private List<String> allergies;

        public MedicalRecordResource(PersonWithMedicalRecordsValueObject.MedicalRecordValueObject medicalRecord) {
            this.medications = medicalRecord
                    .medications()
                    .stream()
                    .map(MedicationResource::new)
                    .collect(Collectors.toList());
            this.allergies = medicalRecord.allergies();
        }

        public List<String> getMedications() {
            return medications
                    .stream().map(MedicationResource::toString)
                    .collect(Collectors.toList());
        }
    }

    @NoArgsConstructor
    public static class MedicationResource {
        private String drug;
        private String dose;

        public MedicationResource(PersonWithMedicalRecordsValueObject.MedicationValueObject medication) {
            this.drug = medication.drug();
            this.dose = medication.dose();
        }

        @Override
        public String toString() {
            return drug + ":" + dose;
        }
    }
}
