package com.example.safetynetalert.core.domain.persons.query;

import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;

import java.util.List;
import java.util.stream.Collectors;

public record PersonWithMedicalRecordsValueObject(String firstName,
                                                  String lastName,
                                                  String address,
                                                  Integer age,
                                                  MedicalRecordValueObject medicalRecord) {

    public PersonWithMedicalRecordsValueObject(PersonAggregate m) {
        this(m.firstName(),
                m.lastName(),
                m.address(),
                m.age(),
                new MedicalRecordValueObject(m.medicalRecord()));
    }

    public record MedicalRecordValueObject(List<MedicationValueObject> medications,
                                           List<String> allergies) {

        public MedicalRecordValueObject(PersonAggregate.Medication.MedicalRecord medicalRecord) {
            this(medicalRecord
                            .medications()
                            .stream()
                            .map(MedicationValueObject::new)
                            .collect(Collectors.toList()),
                    medicalRecord.allergies());
        }
    }

    public record MedicationValueObject(
            String drug,
            String dose) {

        public MedicationValueObject(PersonAggregate.Medication medication) {
            this(medication.drug(), medication.dose());
        }
    }
}
