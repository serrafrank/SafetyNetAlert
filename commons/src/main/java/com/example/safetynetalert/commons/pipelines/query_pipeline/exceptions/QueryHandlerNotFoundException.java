package com.example.safetynetalert.commons.pipelines.query_pipeline.exceptions;

import com.example.safetynetalert.commons.exception.GenericInternalServerErrorException;
import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;

public class QueryHandlerNotFoundException
        extends GenericInternalServerErrorException {

    private final String queryClass;

    public <Q extends Query> QueryHandlerNotFoundException(
            Q query) {
        this.queryClass = query.getClass().getSimpleName();
    }

    @Override
    public String getMessage() {
        return "Cannot find a matching handler for " + queryClass + " query";
    }
}
