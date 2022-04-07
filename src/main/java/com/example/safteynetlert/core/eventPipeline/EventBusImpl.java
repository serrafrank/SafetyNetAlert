package com.example.safteynetlert.core.eventPipeline;

import com.example.safteynetlert.core.eventPipeline.exceptions.EventHandlerNotFoundException;
import com.example.safteynetlert.core.pipeline_builder.Pipeline;
import com.example.safteynetlert.core.pipeline_builder.PipelineBuilder;
import com.example.safteynetlert.core.pipeline_builder.PipelineSupplier;
import com.example.safteynetlert.core.pipeline_builder.validators.GenericValidation;
import com.example.safteynetlert.core.pipeline_builder.validators.PipelineValidatorUtil;

public class EventBusImpl implements EventBus {

    private final Pipeline genericPipeline = new PipelineBuilder();

    @Override
    public EventBusImpl handlers(PipelineSupplier.Supply<EventHandler> EventHandlers) {
        this.genericPipeline.handlers(EventHandlers);
        return this;
    }

    @Override
    public EventBusImpl middlewares(PipelineSupplier.Supply<EventMiddleware> middlewares) {
        this.genericPipeline.middlewares(middlewares);
        return this;
    }

    @Override
    public <TEvent extends Event> void dispatch(TEvent event) {
         this.genericPipeline.submit(event)
                .validate(handlers -> GenericValidation.from(handlers)
                        .expected(PipelineValidatorUtil.notEmpty())
                        .orThrow(() -> new EventHandlerNotFoundException(event)))
                .first();
    }
}