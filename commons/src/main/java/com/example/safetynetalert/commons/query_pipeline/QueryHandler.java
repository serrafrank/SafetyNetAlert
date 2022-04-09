package com.example.safetynetalert.commons.query_pipeline;

import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineHandler;

public interface QueryHandler<TRequest, TReturn>
    extends PipelineHandler<TRequest, TReturn> {

    TReturn handler(TRequest request);
}
