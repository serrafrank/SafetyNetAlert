package com.example.safteynetlert.infrastructure.models;

import lombok.Getter;

@Getter
public class Firestation {
    private final Integer station;
    private final String address;

    public Firestation(Integer station, String address) {
        this.station = station;
        this.address = address;
    }
}
