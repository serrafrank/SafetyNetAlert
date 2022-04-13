package com.example.safetynetalert.core.application;

import com.example.safetynetalert.commons.pipelines.command_pipeline.CommandBus;
import com.example.safetynetalert.core.domain.persons.command.CreatePersonCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandUseCaseImpl
    implements CommandUseCase {

    private final CommandBus commandBus;

    @Autowired
    public CommandUseCaseImpl(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void createPerson(String firstName,
                             String lastName,
                             String address,
                             String city,
                             String zip,
                             String phone,
                             String email) {

        var createPersonCommand = new CreatePersonCommand(firstName,
            lastName,
            address,
            city,
            zip,
            phone,
            email);

        commandBus.dispatch(createPersonCommand);
    }

    @Override
    public void editPerson() {

    }

    @Override
    public void deletePerson() {

    }
}
