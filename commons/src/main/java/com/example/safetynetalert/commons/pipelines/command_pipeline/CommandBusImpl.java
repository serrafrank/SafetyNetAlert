package com.example.safetynetalert.commons.pipelines.command_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.Pipeline;
import com.example.safetynetalert.commons.pipeline_builder.PipelineBuilder;
import com.example.safetynetalert.commons.pipeline_builder.PipelineSupplier.Supply;
import com.example.safetynetalert.commons.pipeline_builder.validators.GenericValidation;
import com.example.safetynetalert.commons.pipeline_builder.validators.PipelineValidatorUtil;
import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHandlerNotFoundException;
import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHasMultipleHandlersException;
import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;
import com.example.safetynetalert.commons.pipelines.event_pipeline.EventBus;

public class CommandBusImpl
    implements CommandBus {

    private final Pipeline genericPipeline = new PipelineBuilder();
    private EventBus eventBus;

    @Override
    public CommandBus handlers(Supply<CommandHandler<? extends Command, ?>> commandHandlers) {
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
    public <TCommand extends Command, TReturn> TReturn dispatch(
        TCommand command) {
        var dispatcher = this.genericPipeline.submit(command)
            .validate(handlers -> GenericValidation.from(
                handlers)
                .expected(PipelineValidatorUtil.notEmpty())
                .orThrow(() -> new CommandHandlerNotFoundException(
                    command)))
            .validate(handlers -> GenericValidation.from(
                handlers)
                .expected(PipelineValidatorUtil.onlyOne())
                .orThrow(() -> new CommandHasMultipleHandlersException(
                    command,
                    handlers)));

        if (eventBus != null) {
            var handler = (CommandHandler) dispatcher.handler();
            handler.events()
                .forEach(event -> eventBus.dispatch((Event) event));
        }

        return dispatcher.first();
    }
}
