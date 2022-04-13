package com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions;

import com.example.safetynetalert.commons.exception.GenericInternalServerErrorException;
import com.example.safetynetalert.commons.pipelines.command_pipeline.Command;

public class CommandHandlerNotFoundException
        extends GenericInternalServerErrorException {

    private final String commandClass;

    public <C extends Command> CommandHandlerNotFoundException(
            C command) {
        this.commandClass = command.getClass().getSimpleName();
    }

    @Override
    public String getMessage() {
        return "Cannot find a matching handler for " + commandClass + " command";
    }
}
