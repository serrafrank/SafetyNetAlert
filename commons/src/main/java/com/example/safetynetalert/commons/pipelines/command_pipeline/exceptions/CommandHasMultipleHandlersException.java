package com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions;

import com.example.safetynetalert.commons.pipelines.command_pipeline.Command;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineHandler;
import java.util.List;
import java.util.stream.Collectors;

public class CommandHasMultipleHandlersException
    extends RuntimeException {

    private final String message;

    public <TCommand extends Command> CommandHasMultipleHandlersException(
        TCommand command,
        List<? extends PipelineHandler> matchingHandlers) {
        String commandName = command.getClass().getSimpleName();
        String handlerNames =
            matchingHandlers.stream()
                .map(it -> it.getClass().getSimpleName())
                .collect(Collectors.joining(", "));

        this.message =
            "Command "
            + commandName
            + " must have a single matching handler, but found "
            + matchingHandlers.size()
            + " ("
            + handlerNames
            + ")";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
