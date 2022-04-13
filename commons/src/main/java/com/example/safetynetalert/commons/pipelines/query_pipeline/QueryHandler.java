package com.example.safetynetalert.commons.pipelines.query_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.PipelineHandler;

public interface QueryHandler<Q, R>
    extends PipelineHandler<Q, R> {

    R handler(Q request);

    default R handleRequest(Q request) {
        return handler(request);
    }
}
