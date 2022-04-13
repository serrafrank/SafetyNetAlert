package com.example.safetynetalert.commons.pipelines.event_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.Pipeline;
import com.example.safetynetalert.commons.pipeline_builder.PipelineBuilder;
import com.example.safetynetalert.commons.pipeline_builder.PipelineSupplier.Supply;
public class EventBusImpl
    implements EventBus {

    private final Pipeline genericPipeline = new PipelineBuilder();

    @Override
    public EventBusImpl handlers(Supply<EventHandler<? extends Event>> eventHandlers) {
        this.genericPipeline.handlers(eventHandlers);
        return this;
    }

    @Override
    public EventBusImpl middlewares(Supply<EventMiddleware> middlewares) {
        this.genericPipeline.middlewares(middlewares);
        return this;
    }

    @Override
    public <E extends Event> void dispatch(E event) {
        this.genericPipeline.submit(event)
            .dispatch();
    }
}
