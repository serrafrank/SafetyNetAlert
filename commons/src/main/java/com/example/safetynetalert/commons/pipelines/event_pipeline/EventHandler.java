package com.example.safetynetalert.commons.pipelines.event_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.PipelineHandler;

public interface EventHandler<E>
    extends PipelineHandler<E, Void> {

    void handler(E request);

    default Void handleRequest(E request) {
        handler(request);
        return null;
    }
}
