package com.example.safetynetalert.commons.pipelines.command_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.Pipeline;
import com.example.safetynetalert.commons.pipeline_builder.PipelineBuilder;
import com.example.safetynetalert.commons.pipeline_builder.PipelineSupplier.Supply;
import com.example.safetynetalert.commons.pipeline_builder.validators.PipelineValidatorUtil;
import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHandlerNotFoundException;
import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHasMultipleHandlersException;
import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;
import com.example.safetynetalert.commons.pipelines.event_pipeline.EventBus;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;

public class CommandBusImpl
        implements CommandBus {

    private final Pipeline genericPipeline = new PipelineBuilder();
    private EventBus eventBus;

    @Override
    public CommandBus handlers(Supply<CommandHandler<? extends Command>> commandHandlers) {
        this.genericPipeline.handlers(commandHandlers);
        return this;
    }

    @Override
    public CommandBus middlewares(Supply<CommandMiddleware> middlewares) {
        this.genericPipeline.middlewares(middlewares);
        return this;
    }

    @Override
    public CommandBus eventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        return this;
    }

    @Override
    public <C extends Command> void dispatch(C command) {
        var dispatcher = this.genericPipeline.submit(command)
            .validate(handlers -> PrettyValidation.test(
                handlers)
                .is(PipelineValidatorUtil.notEmpty())
                .orThrow(() -> new CommandHandlerNotFoundException(
                    command)))
            .validate(handlers -> PrettyValidation.test(
                handlers)
                .is(PipelineValidatorUtil.onlyOne())
                .orThrow(() -> new CommandHasMultipleHandlersException(
                                command,
                                handlers)));

        dispatcher.dispatch();
        if (eventBus != null) {
            var handler = (AbstractCommandHandler<C>) dispatcher.handler();
            handler.events().forEach(event -> eventBus.dispatch((Event) event));
        }
    }
}
