package com.example.safetynetalert.core.domain.persons.command;

import com.example.safetynetalert.commons.pipelines.command_pipeline.AbstractCommandHandler;
import com.example.safetynetalert.core.domain.persons.aggregate.PersonAggregate;
import com.example.safetynetalert.core.domain.persons.command.events.CreatePersonEvent;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonCommandHandler extends AbstractCommandHandler<CreatePersonCommand> {

    @Override
    public void handler(CreatePersonCommand command) {
        var aggregate = new PersonAggregate(
            command.getFirstName(),
            command.getLastName(),
            command.getAddress(),
            command.getCity(),
            command.getZip(),
            command.getPhone(),
            command.getEmail()
        );

        addEvent(new CreatePersonEvent(command, aggregate));
    }
}
