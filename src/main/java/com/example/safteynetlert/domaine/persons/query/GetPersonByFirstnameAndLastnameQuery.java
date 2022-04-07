package com.example.safteynetlert.domaine.persons.query;

import com.example.safteynetlert.core.queryPipeline.Query;

public record GetPersonByFirstnameAndLastnameQuery(String firstname,
                                                   String lastname) implements Query {
}
