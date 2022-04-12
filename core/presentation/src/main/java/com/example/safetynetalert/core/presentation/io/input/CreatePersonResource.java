package com.example.safetynetalert.core.presentation.io.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CreatePersonResource {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String zip;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String email;
}
