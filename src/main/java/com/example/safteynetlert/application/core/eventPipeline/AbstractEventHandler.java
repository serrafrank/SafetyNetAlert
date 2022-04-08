package com.example.safteynetlert.application.core.eventPipeline;

public abstract class AbstractEventHandler<TRequest, TReturn>
        implements EventHandler<TRequest, TReturn> {

    @Override
    public TReturn handleRequest(TRequest request) {
        return handler(request);
    }

}