package com.example.safetynetalert.core.application;

import com.example.safetynetalert.commons.pipelines.command_pipeline.CommandBus;
import com.example.safetynetalert.core.domain.persons.command.CreatePersonCommand;
import org.springframework.beans.factory.annotation.Autowired;

public class CommandUseCaseImpl implements CommandUseCase{


    private final CommandBus commandBus;

    @Autowired
    public CommandUseCaseImpl(CommandBus commandBus) {
        this.commandBus = commandBus;
    }
    
    @Override
    public void createPerson() {
        commandBus.dispatch()
        new CreatePersonCommand();
    }

    @Override
    public void editPerson() {

    }

    @Override
    public void deletePerson() {

    }
}
