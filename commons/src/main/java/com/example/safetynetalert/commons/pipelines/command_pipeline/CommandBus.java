package com.example.safetynetalert.commons.pipelines.command_pipeline;

import com.example.safetynetalert.commons.pipelines.event_pipeline.EventBus;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineSupplier.Supply;

public interface CommandBus {

    CommandBus handlers(Supply<CommandHandler<? extends Command, ?>> requestHandlers);

    CommandBus middlewares(Supply<CommandMiddleware> middlewares);

    CommandBus eventBus(EventBus eventBus);

    <TRequest extends Command, TReturn> TReturn dispatch(TRequest request);
}
