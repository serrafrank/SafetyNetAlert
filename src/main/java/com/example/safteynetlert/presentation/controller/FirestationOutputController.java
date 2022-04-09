package com.example.safteynetlert.presentation.controller;

import com.example.safteynetlert.application.QueryUseCase;
import com.example.safteynetlert.presentation.io.output.AllPersonsByFirestationNumberResource;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationOutputController {

    private final QueryUseCase queryUseCaser;

    public FirestationOutputController(QueryUseCase queryUseCaser) {
        this.queryUseCaser = queryUseCaser;
    }

    @GetMapping("firestation")
    public AllPersonsByFirestationNumberResource getAllPersonsByFirestationNumber(@RequestParam("stationNumber") Integer stationNumber){
        var personsByFirestation = queryUseCaser.getPersonsByFirestation(stationNumber);

        return new AllPersonsByFirestationNumberResource(personsByFirestation);
    }

    @GetMapping("phoneAlert")
    public Set<String> getPersonPhoneNumbersByFirestationNumber(@RequestParam("stationNumber") Integer stationNumber){
        return queryUseCaser.getPersonPhoneNumbersByFirestationNumber(stationNumber);
    }
}
