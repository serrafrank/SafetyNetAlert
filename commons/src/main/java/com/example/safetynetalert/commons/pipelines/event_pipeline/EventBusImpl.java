package com.example.safetynetalert.commons.pipelines.event_pipeline;

import com.example.safetynetalert.commons.pipelines.event_pipeline.exceptions.EventHandlerNotFoundException;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.Pipeline;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineBuilder;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineSupplier.Supply;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.validators.GenericValidation;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.validators.PipelineValidatorUtil;

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
            .validate(handlers -> GenericValidation.from(
                handlers)
                .expected(PipelineValidatorUtil.notEmpty())
                .orThrow(() -> new EventHandlerNotFoundException(
                    event)))
            .first();
    }
}
