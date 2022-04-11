package com.example.safetynetalert.commons.pipelines.query_pipeline.exceptions;

import com.example.safetynetalert.commons.pipeline_builder.PipelineHandler;
import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import java.util.List;
import java.util.stream.Collectors;

public class QueryHasMultipleHandlersException
    extends RuntimeException {

    private final String message;

    public <TQuery extends Query> QueryHasMultipleHandlersException(
        TQuery query,
        List<? extends PipelineHandler> matchingHandlers) {
        String queryName = query.getClass().getSimpleName();
        String handlerNames =
            matchingHandlers.stream()
                .map(it -> it.getClass().getSimpleName())
                .collect(Collectors.joining(", "));

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
