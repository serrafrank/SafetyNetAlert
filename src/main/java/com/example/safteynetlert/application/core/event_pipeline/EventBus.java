package com.example.safteynetlert.application.core.event_pipeline;

import com.example.safteynetlert.domaine.pipeline_builder.PipelineSupplier;

public interface EventBus {

    EventBusImpl handlers(PipelineSupplier.Supply<EventHandler> requestHandlers);

    EventBusImpl middlewares(PipelineSupplier.Supply<EventMiddleware> middlewares);

    <TRequest extends Event> void dispatch(TRequest request);
}
