package com.example.safetynetalert.commons.pipelines.event_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.Pipeline;
import com.example.safetynetalert.commons.pipeline_builder.PipelineBuilder;
import com.example.safetynetalert.commons.pipeline_builder.PipelineSupplier.Supply;
import com.example.safetynetalert.commons.pipeline_builder.validators.PipelineValidatorUtil;
import com.example.safetynetalert.commons.pipelines.event_pipeline.exceptions.EventHandlerNotFoundException;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;

public class EventBusImpl
        implements EventBus {

    private final Pipeline genericPipeline = new PipelineBuilder();

    @Override
    public EventBusImpl handlers(Supply<EventHandler<? extends Event, ?>> EventHandlers) {
        this.genericPipeline.handlers(EventHandlers);
        return this;
    }

    @Override
    public EventBusImpl middlewares(Supply<EventMiddleware> middlewares) {
        this.genericPipeline.middlewares(middlewares);
        return this;
    }

    @Override
    public <TEvent extends Event> void dispatch(TEvent event) {
        this.genericPipeline.submit(event)
                .validate(handlers -> PrettyValidation.test(
                                handlers)
                        .is(PipelineValidatorUtil.notEmpty())
                        .orThrow(() -> new EventHandlerNotFoundException(
                                event)))
                .first();
    }
}
