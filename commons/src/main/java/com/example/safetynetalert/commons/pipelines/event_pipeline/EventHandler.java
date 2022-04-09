package com.example.safetynetalert.commons.pipelines.event_pipeline;

import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineHandler;

public interface EventHandler<TRequest, TReturn>
    extends PipelineHandler<TRequest, TReturn> {

    TReturn handler(TRequest request);
}
