package com.example.safteynetlert.presentation.controller;

import com.example.safteynetlert.application.QueryUseCase;
import com.example.safteynetlert.domaine.persons.query.PersonByFirstnameAndLastnameValueObject;
import com.example.safteynetlert.presentation.exceptions.ResourceNotFoundException;
import com.example.safteynetlert.presentation.io.output.ChildByAddressWithFamilyMembersResourse;
import com.example.safteynetlert.presentation.io.output.PersonByFirstnameAndLastnameResource;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonOutputController {

    private final QueryUseCase queryUseCaser;

    public PersonOutputController(QueryUseCase queryUseCaser) {
        this.queryUseCaser = queryUseCaser;
    }

    @GetMapping("personInfo")
    public PersonByFirstnameAndLastnameResource getPersonInfo(@RequestParam("firstName") String firstName,
                                                              @RequestParam("lastName") String lastName) {

        PersonByFirstnameAndLastnameValueObject person = queryUseCaser.getPersonByFirstnameAndLastname(
            firstName,
            lastName)
            .orElseThrow(ResourceNotFoundException::new);

        return new PersonByFirstnameAndLastnameResource(person);
    }


    @GetMapping("childAlert")
    public Set<ChildByAddressWithFamilyMembersResourse> getChildrenByAddressWithFamilyMembers(@RequestParam("address") String address) {

        return queryUseCaser.getChildrenByAddressWithFamilyMembers(address)
            .stream().map(ChildByAddressWithFamilyMembersResourse::new)
            .collect(Collectors.toSet());
    }

}
