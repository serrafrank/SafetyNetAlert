package com.example.safetynetalert.commons.pipelines.command_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.PipelineSupplier.Supply;
import com.example.safetynetalert.commons.pipelines.event_pipeline.EventBus;

public interface CommandBus {

    CommandBus handlers(Supply<CommandHandler<? extends Command>> requestHandlers);

    CommandBus middlewares(Supply<CommandMiddleware> middlewares);

    CommandBus eventBus(EventBus eventBus);

    <C extends Command> void dispatch(C request);
}
