package com.example.safetynetalert.core.domain.persons.command.events;

import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;
import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;
import com.example.safetynetalert.core.domain.persons.command.CreatePersonCommand;
import lombok.Getter;

public class CreatePersonEvent extends Event {

    @Getter
    private final PersonAggregate aggregate;

    public CreatePersonEvent(CreatePersonCommand command, PersonAggregate aggregate) {
        super(command);
        this.aggregate = aggregate;
    }
}
