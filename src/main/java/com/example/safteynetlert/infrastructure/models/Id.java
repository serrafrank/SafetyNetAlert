package com.example.safteynetlert.infrastructure.models;

import lombok.Getter;

@Getter
public final class Id {
    private final String firstName;
    private final String lastName;

    public Id(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
