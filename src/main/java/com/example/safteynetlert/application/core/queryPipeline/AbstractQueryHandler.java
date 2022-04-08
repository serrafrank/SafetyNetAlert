package com.example.safteynetlert.application.core.queryPipeline;

public abstract class AbstractQueryHandler<TRequest, TReturn>
        implements QueryHandler<TRequest, TReturn> {

    @Override
    public TReturn handleRequest(TRequest request) {
        return handler(request);
    }
}