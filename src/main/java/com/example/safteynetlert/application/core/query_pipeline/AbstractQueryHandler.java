package com.example.safteynetlert.application.core.query_pipeline;

public abstract class AbstractQueryHandler<TRequest extends Query, TReturn>
        implements QueryHandler<TRequest, TReturn> {

    @Override
    public TReturn handleRequest(TRequest request) {
        return handler(request);
    }
}
