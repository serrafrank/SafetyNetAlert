package com.example.safteynetlert.core.commandPipeline;

import com.example.safteynetlert.core.eventPipeline.EventBus;
import com.example.safteynetlert.core.pipeline_builder.PipelineSupplier;

public interface CommandBus {

    CommandBus handlers(PipelineSupplier.Supply<CommandHandler> requestHandlers);

    CommandBus middlewares(PipelineSupplier.Supply<CommandMiddleware> middlewares);

    CommandBus eventBus(EventBus eventBus);

    <TRequest extends Command, TReturn> TReturn dispatch(TRequest request);
}
