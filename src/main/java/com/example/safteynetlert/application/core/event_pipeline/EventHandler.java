package com.example.safteynetlert.application.core.event_pipeline;

import com.example.safteynetlert.domaine.pipeline_builder.PipelineHandler;

public interface EventHandler<TRequest, TReturn>
        extends PipelineHandler<TRequest, TReturn> {
    TReturn handler(TRequest request);
}
