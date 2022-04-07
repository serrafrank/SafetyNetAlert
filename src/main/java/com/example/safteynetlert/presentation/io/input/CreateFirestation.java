package com.example.safteynetlert.presentation.io.input;

public class CreateFirestation {
    private final Integer station;
    private final String address;

    public CreateFirestation(Integer station, String address) {
        this.station = station;
        this.address = address;
    }

    public Integer getStation() {
        return station;
    }

    public String getAddress() {
        return address;
    }
}
