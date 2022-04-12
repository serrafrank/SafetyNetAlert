package com.example.safetynetalert.core.domain.persons.aggregate;

import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.core.domain.exceptions.BlankArgumentException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public record PersonAggregate(
        String firstName,
        String lastName,
        String address,
        String city,
        String zip,
        String phone,
        String email,
        LocalDate birthdate,
        Medication.MedicalRecord medicalRecord) {

    public PersonAggregate {
        PrettyValidation.test(firstName).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
        PrettyValidation.test(lastName).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
    }

    public PersonAggregate(
            String firstName,
            String lastName,
            String address,
            String city,
            String zip,
            String phone, String email) {
        this(firstName, lastName, address, city, zip, phone, email, null, null);
    }

    public PersonAggregate(
            String firstName,
            String lastName,
            LocalDate birthdate,
            Medication.MedicalRecord medicalRecord) {
        this(firstName, lastName, null, null, null, null, null, birthdate, medicalRecord);
    }

    public Id id() {
        return new Id(firstName, lastName);
    }

    public Integer age() {
        return Period.between(birthdate, LocalDate.now())
                .getYears();
    }

    public void addMedication(Medication medication) {
        this.medicalRecord.medications().add(medication);
    }

    public void addMedication(String drug, String dose) {
        this.medicalRecord.medications().add(new Medication(drug, dose));
    }

    public void addAllergy(String allergy) {
        this.medicalRecord.allergies().add(allergy);
    }

    public boolean isMinor() {
        return age() <= 18;
    }

    public static record Medication(
            String drug,
            String dose) {

        public Medication {
            PrettyValidation.test(drug).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
            PrettyValidation.test(dose).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
        }

        public static record MedicalRecord(List<Medication> medications,
                                           List<String> allergies) {

        }
    }

    public static record Id(String id) {

        public Id {
            PrettyValidation.test(id).isNot(StringUtils::isBlank).orThrow(BlankArgumentException::new);
        }

        public Id(String... elements) {
            this(String.join(":", elements));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Id testerId = (Id) o;

            return id.equals(testerId.id());
        }
    }
}
