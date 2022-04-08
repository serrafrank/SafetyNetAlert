package com.example.safteynetlert.domaine.firestations;

import com.example.safteynetlert.presentation.io.input.CreateFirestation;

import java.util.List;

public interface FirestationDomain {

    void createFirestation(CreateFirestation createFirestation);

    List<FirestationAggregate> getFirestationsByStationNumber(
            Integer firestationNumber);
}
