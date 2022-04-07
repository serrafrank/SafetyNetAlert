package com.example.safteynetlert.core.eventPipeline;

import com.example.safteynetlert.core.pipeline_builder.PipelineHandler;

public interface EventHandler<TRequest, TReturn>
        extends PipelineHandler<TRequest, TReturn> {
    TReturn handler(TRequest request);
}
