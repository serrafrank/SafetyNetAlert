package com.example.safteynetlert.application.core.query_pipeline;

import com.example.safteynetlert.domaine.pipeline_builder.PipelineHandler;

public interface QueryHandler<TRequest, TReturn>
        extends PipelineHandler<TRequest, TReturn> {
    TReturn handler(TRequest request);
}
