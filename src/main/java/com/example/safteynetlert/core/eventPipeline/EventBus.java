package com.example.safteynetlert.core.eventPipeline;

import com.example.safteynetlert.core.pipeline_builder.PipelineSupplier;

public interface EventBus {

    EventBusImpl handlers(PipelineSupplier.Supply<EventHandler> requestHandlers);

    EventBusImpl middlewares(PipelineSupplier.Supply<EventMiddleware> middlewares);

    <TRequest extends Event> void dispatch(TRequest request);
}
