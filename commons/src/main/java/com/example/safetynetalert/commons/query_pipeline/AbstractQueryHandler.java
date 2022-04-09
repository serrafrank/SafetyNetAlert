package com.example.safetynetalert.commons.query_pipeline;

public abstract class AbstractQueryHandler<TRequest extends Query, TReturn extends Object>
    implements QueryHandler<TRequest, TReturn> {

    @Override
    public TReturn handleRequest(TRequest request) {
        return handler(request);
    }
}
