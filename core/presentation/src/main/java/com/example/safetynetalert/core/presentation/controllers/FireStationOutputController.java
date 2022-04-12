package com.example.safetynetalert.core.presentation.controllers;

import com.example.safetynetalert.core.application.QueryUseCase;
import com.example.safetynetalert.core.presentation.io.output.AllPersonsByFireStationNumberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class FireStationOutputController {

    private final QueryUseCase queryUseCase;

    public FireStationOutputController(QueryUseCase queryUseCase) {
        this.queryUseCase = queryUseCase;
    }

    @GetMapping("firestation")
    public ResponseEntity<AllPersonsByFireStationNumberResponse> getAllPersonsByFireStationNumber(@RequestParam("stationNumber") Integer stationNumber) {
        var personsByFireStation = queryUseCase.getPersonsByFireStation(stationNumber);
        var resource = new AllPersonsByFireStationNumberResponse(personsByFireStation);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("phoneAlert")
    public ResponseEntity<Set<String>> getPersonPhoneNumbersByFireStationNumber(@RequestParam("stationNumber") Integer stationNumber) {
        var resource = queryUseCase.getPersonPhoneNumbersByFireStationNumber(stationNumber);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
