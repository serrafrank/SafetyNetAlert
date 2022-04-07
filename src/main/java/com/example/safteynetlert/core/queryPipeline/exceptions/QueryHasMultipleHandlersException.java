package com.example.safteynetlert.core.queryPipeline.exceptions;

import com.example.safteynetlert.core.queryPipeline.Query;
import com.example.safteynetlert.core.pipeline_builder.PipelineHandler;

import java.util.List;
import java.util.stream.Collectors;

public class QueryHasMultipleHandlersException
        extends RuntimeException {

    private final String message;

    public <TQuery extends Query> QueryHasMultipleHandlersException(TQuery query, List<? extends PipelineHandler> matchingHandlers) {
        String queryName = query.getClass().getSimpleName();
        String handlerNames =
                matchingHandlers.stream().map(it -> it.getClass().getSimpleName()).collect(Collectors.joining(", "));

        this.message =
                "Query "
                        + queryName
                        + " must have a single matching handler, but found "
                        + matchingHandlers.size()
                        + " ("
                        + handlerNames
                        + ")";
    }

    @Override
    public String getMessage() {
        return message;
    }
}