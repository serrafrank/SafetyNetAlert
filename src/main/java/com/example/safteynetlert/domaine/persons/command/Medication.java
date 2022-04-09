package com.example.safteynetlert.domaine.persons.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record Medication(
      String drug,
      String dose) {

    @Override
    public String toString() {
        return drug + ":" + dose;
    }
}
