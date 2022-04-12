package com.example.safetynetalert.core.presentation.controllers;

import com.example.safetynetalert.core.application.QueryUseCase;
import com.example.safetynetalert.core.presentation.io.output.AllPersonsByFireStationNumberResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class FirestationOutputController {

    private final QueryUseCase queryUseCaser;

    public FirestationOutputController(QueryUseCase queryUseCaser) {
        this.queryUseCaser = queryUseCaser;
    }

    @GetMapping("firestation")
    public AllPersonsByFireStationNumberResource getAllPersonsByFirestationNumber(@RequestParam("stationNumber") Integer stationNumber) {
        var personsByFirestation = queryUseCaser.getPersonsByFireStation(stationNumber);

        return new AllPersonsByFireStationNumberResource(personsByFirestation);
    }

    @GetMapping("phoneAlert")
    public Set<String> getPersonPhoneNumbersByFirestationNumber(@RequestParam("stationNumber") Integer stationNumber) {
        return queryUseCaser.getPersonPhoneNumbersByFireStationNumber(stationNumber);
    }
}
