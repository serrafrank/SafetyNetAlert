package com.example.safteynetlert.application.core.queryPipeline;

import com.example.safteynetlert.domaine.pipeline_builder.PipelineHandler;

public interface QueryHandler<TRequest, TReturn>
        extends PipelineHandler<TRequest, TReturn> {
    TReturn handler(TRequest request);
}
