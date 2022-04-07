package com.example.safteynetlert.core.commandPipeline;

import com.example.safteynetlert.core.commandPipeline.exceptions.CommandHandlerNotFoundException;
import com.example.safteynetlert.core.commandPipeline.exceptions.CommandHasMultipleHandlersException;
import com.example.safteynetlert.core.eventPipeline.Event;
import com.example.safteynetlert.core.eventPipeline.EventBus;
import com.example.safteynetlert.core.pipeline_builder.Pipeline;
import com.example.safteynetlert.core.pipeline_builder.PipelineBuilder;
import com.example.safteynetlert.core.pipeline_builder.PipelineSupplier;
import com.example.safteynetlert.core.pipeline_builder.validators.GenericValidation;
import com.example.safteynetlert.core.pipeline_builder.validators.PipelineValidatorUtil;

public class CommandBusImpl implements CommandBus {

    private final Pipeline genericPipeline = new PipelineBuilder();
    private EventBus eventBus;

    @Override
    public CommandBus handlers(PipelineSupplier.Supply<CommandHandler> CommandHandlers) {
        this.genericPipeline.handlers(CommandHandlers);
        return this;
    }

    @Override
    public CommandBus middlewares(PipelineSupplier.Supply<CommandMiddleware> middlewares) {
        this.genericPipeline.middlewares(middlewares);
        return this;
    }

    @Override
    public CommandBus eventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        return this;
    }

    @Override
    public <TCommand extends Command, TReturn> TReturn dispatch(TCommand command) {
        var dispatcher = this.genericPipeline.submit(command)
                .validate(handlers -> GenericValidation.from(handlers)
                        .expected(PipelineValidatorUtil.notEmpty())
                        .orThrow(() -> new CommandHandlerNotFoundException(command)))
                .validate(handlers -> GenericValidation.from(handlers)
                        .expected(PipelineValidatorUtil.onlyOne())
                        .orThrow(() -> new CommandHasMultipleHandlersException(command,
                                handlers)));

        if(eventBus != null){
            var handler = (CommandHandler) dispatcher.handler();
            handler.events().forEach(event -> eventBus.dispatch((Event) event));
        }

        return dispatcher.first();
    }
}