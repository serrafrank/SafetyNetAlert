package com.example.safetynetalert.core.domain.persons.aggregate;

import java.util.List;

public record MedicalRecord(List<Medication> medications,
                            List<String> allergies) {

}
