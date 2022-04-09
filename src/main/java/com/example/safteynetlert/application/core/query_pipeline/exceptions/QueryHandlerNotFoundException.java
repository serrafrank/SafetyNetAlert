package com.example.safteynetlert.application.core.query_pipeline.exceptions;

import com.example.safteynetlert.application.core.query_pipeline.Query;

public class QueryHandlerNotFoundException
        extends RuntimeException {

    private final String queryClass;

    public <TQuery extends Query> QueryHandlerNotFoundException(
            TQuery query) {
        this.queryClass = query.getClass().getSimpleName();
    }

    @Override
    public String getMessage() {
        return "Cannot find a matching handler for " + queryClass + " query";
    }
}