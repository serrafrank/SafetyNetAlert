package com.example.safteynetlert.domaine.persons.command;

public record Medication(String drug,
                         String dose) {

    @Override
    public String toString() {
        return drug + ":" + dose;
    }
}
