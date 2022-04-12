package com.example.safetynetalert.core.infrastructure.users.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class FireStationModel {

    @Getter
    @Setter
    private Integer station;

    @Getter
    @Setter
    private String address;

    public FireStationModel(Integer station, String address) {
        this.station = station;
        this.address = address;
    }
}
