package com.example.safteynetlert.core.queryPipeline;

import com.example.safteynetlert.core.pipeline_builder.PipelineHandler;

public interface QueryHandler <TRequest, TReturn>
        extends PipelineHandler<TRequest, TReturn> {
    TReturn handler(TRequest request);
}
