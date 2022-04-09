package com.example.safetynetalert.commons.pipelines.event_pipeline;

import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineSupplier.Supply;

public interface EventBus {

    EventBusImpl handlers(Supply<EventHandler<? extends Event, ?>> requestHandlers);

    EventBusImpl middlewares(Supply<EventMiddleware> middlewares);

    <TRequest extends Event> void dispatch(TRequest request);
}
