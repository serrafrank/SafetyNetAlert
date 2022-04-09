package com.example.safteynetlert.application.core.command_pipeline;

import com.example.safteynetlert.application.core.event_pipeline.EventBus;
import com.example.safteynetlert.domaine.pipeline_builder.PipelineSupplier;

public interface CommandBus {

    CommandBus handlers(PipelineSupplier.Supply<CommandHandler> requestHandlers);

    CommandBus middlewares(PipelineSupplier.Supply<CommandMiddleware> middlewares);

    CommandBus eventBus(EventBus eventBus);

    <TRequest extends Command, TReturn> TReturn dispatch(TRequest request);
}
