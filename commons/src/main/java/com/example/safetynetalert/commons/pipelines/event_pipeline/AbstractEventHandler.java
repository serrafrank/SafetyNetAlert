package com.example.safetynetalert.commons.pipelines.event_pipeline;

public abstract class AbstractEventHandler<TRequest, TReturn>
        implements EventHandler<TRequest, TReturn> {

    @Override
    public TReturn handleRequest(TRequest request) {
        return handler(request);
    }

}
