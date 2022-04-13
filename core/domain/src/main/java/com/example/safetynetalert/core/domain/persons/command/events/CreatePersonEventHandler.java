package com.example.safetynetalert.core.domain.persons.command.events;

import com.example.safetynetalert.commons.pipelines.event_pipeline.EventHandler;
import com.example.safetynetalert.core.domain.persons.command.PersonAggregatorRepository;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonEventHandler
    implements EventHandler<CreatePersonEvent> {

    private final PersonAggregatorRepository repository;

    public CreatePersonEventHandler(PersonAggregatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handler(CreatePersonEvent request) {
        repository.createPerson(request.getAggregate());
    }
}
